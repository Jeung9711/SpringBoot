package com.example.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.DTO.BoardDTO;
import com.example.board.DTO.CommentDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.FileAtch;
import com.example.board.entity.Like;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.FileAtchRepository;
import com.example.board.repository.LikeRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BoardController {
	@Autowired BoardRepository boardRepository;
	@Autowired LikeRepository likeRepository;
	@Autowired CommentRepository commentRepository;
	@Autowired FileAtchRepository fileAtchRepository;
	
	@GetMapping("/board/delete/{id}")
	public String boardDelete(
		@PathVariable Long id
	) {
		boardRepository.deleteById(id);
		return "redirect:/board";
	}

	@GetMapping("/board/update")
	public String boardUpdate(
		Model model,
		HttpSession session,
		@RequestParam Long id
	) {
		Optional<Board> opt = boardRepository.findById(id);
		BoardDTO dto = BoardDTO.toDto(opt);
		model.addAttribute("board", dto);
		return "board/update";
	}
		
	@PostMapping("/board/update")
	public String boardUpdatePost(
		HttpSession session,
		@RequestParam Long id,
		@ModelAttribute BoardDTO boardDTO
		) {
		Optional<Board> opt = boardRepository.findById(id);
		Board post = opt.get();
		post.setTitle(boardDTO.getTitle());
		post.setContent(boardDTO.getContent());

		boardRepository.save(post);
		return "redirect:/board/view?id="+id;
	}

	@GetMapping("/board/view")
	public String boardView(
		@RequestParam Long id,
		@RequestParam(defaultValue = "1") int page,
		HttpSession session,
		Model model ) {
		
		// 게시글 정보 가져오기
		session.setAttribute("boardId", id);
		Optional<Board> opt = boardRepository.findById(id);
		BoardDTO board = BoardDTO.toDto(opt);

		// 게시글 첨부파일 가져오기
		List<FileAtch> files = fileAtchRepository.findByBoardId(id);
		model.addAttribute("images", files);

		// 좋아요 정보 가져오기
		Long likes = likeRepository.countByBoardId(id);
		if(likes == null) {
			likes = (long) 0;
		}
		// 댓글 정보 가져오기
		Pageable pageable = PageRequest.of(page-1, 10);
        Page<Comment> commentPage = commentRepository.findByBoardId(id, pageable);
        Page<CommentDTO> dtoPage = commentPage.map(CommentDTO::toDto);
		
		// 작성자와 조회자 일치 여부 확인
		User sessionUser = (User) session.getAttribute("user_info");
		if(sessionUser != null) {
			Long userId = sessionUser.getId();
			boolean check = board.getUserId().equals(userId);
			model.addAttribute("check", check);

			// 작성자의 좋아요 여부 확인
			Boolean exists = likeRepository.existsByUserIdAndBoardId(userId, id);
			model.addAttribute("liked", exists);
		} else {
			model.addAttribute("check", false);
		}
		model.addAttribute("board", board);
		model.addAttribute("likes", likes);
		model.addAttribute("comments", dtoPage);
		model.addAttribute("commentCount", dtoPage.getTotalElements());
		return "board/view";
	}

	@Transactional
	@PostMapping("/like")
	public String postMethodName(
		@RequestParam Long boardId,
		HttpSession session
	) throws IllegalAccessException {
		User user = (User) session.getAttribute("user_info");
		if(user != null) {
			Board board = boardRepository.findById(boardId)
					.orElseThrow(()-> new IllegalAccessException("해당 게시글이 없습니다."));
					
			if(likeRepository.existsByUserIdAndBoardId(user.getId(), boardId)) {
				likeRepository.deleteByUserIdAndBoardId(user.getId(), boardId);
			} else {
				Like like = new Like();
				like.setBoard(board);
				like.setUser(user);

				likeRepository.save(like);
			}
		}
		return "redirect:/board/view?id="+boardId;
	}
	

	@GetMapping("/board")
	public String board() {

		return "redirect:/board/list";
	}

	@GetMapping("/board/list")
	public String boardList(
		Model model,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "") String search) {
		Pageable pageable = PageRequest.of(page-1, 10);
		Page<Board> boardPage = boardRepository
							.findByTitleContainingOrContentContaining(search, search, pageable);

		Page<BoardDTO> dtoPage = boardPage.map(BoardDTO::toDto);

		model.addAttribute("list",dtoPage);

		int totalPages = dtoPage.getTotalPages();
		int startPage = (page-1) / 10 * 10 +1;
		int endPage = Math.min(startPage+9, startPage);

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		return "board/list";
	}

	@GetMapping("/board/write")
	public String boardWrite() {
		return "board/write";
	}

	@PostMapping("/board/write")
	public String boardWritePost(
		@ModelAttribute Board board,
		@RequestParam MultipartFile file,
		HttpSession session
	) throws IllegalStateException, IOException {
		
		User user = (User) session.getAttribute("user_info");

		board.setUser(user);
		Board savedBoard = boardRepository.save(board);

		FileAtch fileAtch = new FileAtch();

		fileAtch.setUser(user);
		fileAtch.setBoard(savedBoard);

		String oName = file.getOriginalFilename();
		fileAtch.setOName(oName);
		String cName = UUID.randomUUID() + oName.substring(oName.lastIndexOf("."));
		fileAtch.setCName(cName);
		

		fileAtchRepository.save(fileAtch);

		// 실제 파일 저장
		File f = new File("c:/board/" + cName);
		file.transferTo(f);
		
		return "redirect:/board";
	}
}