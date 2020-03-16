package dao;

import datamodel.User;

/**
 *
 */
public interface UserDao {
    /**
     *
     * @param user
     */
    public void create(User user);

    /**
     *
     * @param user
     */
    public void update (User user);

    /**
     *
     * @param id
     */
    public void delete (int id);

    /**
     *
     * @param id
     */
    public User get(int id);
}
