package com.herolynx.users.repositories;

import com.herolynx.users.User;
import org.springframework.data.hazelcast.repository.HazelcastRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends HazelcastRepository<User, String> {

}
