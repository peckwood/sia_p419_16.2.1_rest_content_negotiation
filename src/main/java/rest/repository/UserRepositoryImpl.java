package rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import rest.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public List<User> findUsers() {
		User user1 = new User("raiden", 16);
		User user2 = new User("Judy", 28);
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		return users;
	}

}
