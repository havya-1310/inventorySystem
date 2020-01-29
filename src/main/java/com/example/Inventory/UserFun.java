package com.example.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFun {

    @Autowired
    private MongoTemplate mongoTemplate;

    //HashMap<String, Integer> map = new HashMap<>();
    // public int uuid = 0 ;
    public User saveUser(User user) {

        String userid = user.getUser_id();
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userid));
        User chk = mongoTemplate.findOne(query, User.class);
        if (chk == null) {
            return mongoTemplate.save(user);
        } else {
            return new User("", "", "");
        }
    }
      protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public boolean checkUser(User user) {
        System.out.println("uuuuuu");
        String userid = user.getUser_id();
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userid));
        User chk = mongoTemplate.findOne(query, User.class);

        if (chk == null)
            return false;

        String pass = user.getPassword();

        if (chk.getPassword().equals(pass)) {
            user.setName(chk.getName());
            //uuid = uuid + 1 ;
            //map.put(chk.getUser_id() , uuid) ;
            return true;
        }

        return false;
    }
}
