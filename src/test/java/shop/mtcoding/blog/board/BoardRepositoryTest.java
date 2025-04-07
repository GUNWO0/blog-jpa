package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // BoardRepository
@DataJpaTest // EntityManager, PC
public class BoardRepositoryTest {


    @Autowired // DI (test 코드에서는 생성자 생성이 불가능)
    private BoardRepository boardRepository;

    @Test
    public void findAll_test() {
        // given
        Integer userId = null;

        // when
        List<Board> boardList = boardRepository.findAll(userId);

        // eye
        for (Board board : boardList) {
            System.out.println(board.getTitle() + "," + board.getId());
            System.out.println();
        }

        // Lazy -> Board -> User(id=1)
        // Eager -> N+1  -> Board 조회 -> 연관된 User 유저 수 만큼 주회
        // Eager -> Join -> 한방쿼리
//        System.out.println("--------------------");
//        boardList.get(0).getUser().getUsername();
//        System.out.println("--------------------");


    }
}
