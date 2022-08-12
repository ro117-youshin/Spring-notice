package kr.ac.kopo.ctc.kopo08.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo08.notice.domain.Comment;
import kr.ac.kopo.ctc.kopo08.notice.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment findById(Long id) {
		Comment comment = commentRepository.findById(id).get();
		return comment;
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}

	@Override
	public Comment deleteById(Long id) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(id);
		return null;
	}

}
