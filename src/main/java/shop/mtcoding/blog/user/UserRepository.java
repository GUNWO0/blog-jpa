package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public void save2(User user) {
        em.persist(user);
    }

    public void save(String username, String password, String email) {
        em.createNativeQuery("insert into user_tb (username, password, email) values (?, ?, ?)")
                .setParameter(1, username)
                .setParameter(2, password)
                .setParameter(3, email)
                .executeUpdate();
    }

    public User findByUsername(String username) {
        return (User) em.createNativeQuery("select * from user_tb where username = ?", User.class)
                .setParameter(1, username)
                .getSingleResult();
    }
}
