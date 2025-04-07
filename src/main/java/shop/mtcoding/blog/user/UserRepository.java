package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    /*
        1. createNativeQuery -> 기본쿼리
        2. createQuery -> JPA가 제공해주는 객체지향 쿼리
        3. NameQuery -> Query Method는 함수 이름으로 쿼리생성
        4. EntityGraph ->
     */
    public void save(User user) {
        em.persist(user);
    }


    public User findByUsername(String username) {
        try {
            return em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}


//    public void save(String username, String password, String email) {
//        em.createNativeQuery("insert into user_tb (username, password, email) values (?, ?, ?)")
//                .setParameter(1, username)
//                .setParameter(2, password)
//                .setParameter(3, email)
//                .executeUpdate();
//    }