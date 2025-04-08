package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

// 비지니스 로직, 트랜잭션 처리, DTO 완료
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        try {
            userRepository.save(joinDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("동일한 아이디로 회원가입 하지마라");
        }
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");
        }
        return user;
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        User user = userRepository.findByUsername(username);
        Map<String, Object> dto = new HashMap<>();

        if (user == null) {
            dto.put("available", true);

        } else {
            dto.put("available", false);

        }
        return dto;
    }

    // 1. 영속화 시키기
    @Transactional
    public User 회원정보수정(UserRequest.UpdateDTO updateDTO, Integer userID) {
        User user = userRepository.findById(userID);
        if (user == null) throw new RuntimeException("회원을 찾을 수 없습니다");
        user.update(updateDTO.getPassword(), updateDTO.getEmail()); // 영속화 된 객체의 상태변경
        return user; // 리턴한 이유는 세션을 동기화 해야해서
    } // 더티체킹 - 상태가 변경 되면 업데이트를 날림
}
