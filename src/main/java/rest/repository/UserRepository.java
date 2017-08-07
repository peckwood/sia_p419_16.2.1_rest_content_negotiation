package rest.repository;

import java.util.List;

import rest.entity.User;

public interface UserRepository {
	List<User> findUsers();
}
