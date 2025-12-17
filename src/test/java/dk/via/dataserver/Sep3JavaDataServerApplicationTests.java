package dk.via.dataserver;
import static org.assertj.core.api.Assertions.assertThat;

import dk.via.dataserver.entity.User;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.repository.UserRepository;
import dk.via.dataserver.services.UserServiceDatabase;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest class Sep3JavaDataServerApplicationTests
{
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceDatabase service;

  private static User testUser(int id, String username, String email, Integer points) {
    User u = new User();
    u.setId(id);
    u.setUsername(username);
    u.setPassword("password123654");
    u.setEmail(email);
    u.setPoints(points);
    return u;
  }
  @Test
  @DisplayName("Z:  zero users returns empty list")
  public void z_getAll_zero() {
    when(userRepository.findAll()).thenReturn(List.of());
    Sep3.UserListProto out = service.getAll();
    assertEquals(0, out.getUsersCount());
  }

  @Test
  @DisplayName("O: one user returns exactly one user")
  public void o_getAll_one() {
    User u = testUser(1, "oneUserTest", "one@x.com", 77);
    when(userRepository.findAll()).thenReturn(List.of(u));
    Sep3.UserListProto out = service.getAll();
    assertEquals(1, out.getUsersCount());
    assertEquals(77, out.getUsers(0).getPoints());
    assertEquals("oneUserTest", out.getUsers(0).getUsername());
  }

  @Test
  @DisplayName("M:  many users returns all users correctly")
  public void m_getAll_many() {
    List<User> users = new ArrayList<>();
    users.add(testUser(1, "userUnitTest1", "user1@test.com", 100));
    users.add(testUser(2, "userUnitTest12", "user2@test.com", 200));
    users.add(testUser(3, "userUnitTest13", "user3@test.com", 300));
    users.add(testUser(4, "userUnitTest14", "user4@test.com", 400));
    users.add(testUser(5, "userUnitTest15", "user5@test.com", 500));

    when(userRepository.findAll()).thenReturn(users);
    Sep3.UserListProto out = service.getAll();

    assertEquals(5, out.getUsersCount());
    assertThat(out.getUsersList())
            .extracting(Sep3.UserProto::getUsername)
            .containsExactlyInAnyOrder(
                    "userUnitTest1",
                    "userUnitTest12",
                    "userUnitTest13",
                    "userUnitTest14",
                    "userUnitTest15"
            );
  }

  @Test
  @DisplayName("B: with different payload sizes - 1, 10, 100, 1000 users")
  public void b_getAll_variousSizes() {
    int[] sizes = {1, 10, 100, 1000};

    for (int size : sizes) {
      List<User> users = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        users.add(testUser(i + 1, "user" + i, "user" + i + "@test.com", i * 10));
      }
      when(userRepository.findAll()).thenReturn(users);
      Sep3.UserListProto result = service.getAll();
      assertEquals(size, result.getUsersCount(), "Expected " + size + " users");
    }
  }
  @Test
  @DisplayName("E:  Null Username ")
  public void e_getAll_nullUsername() {

    Sep3.UserProto payload = Sep3.UserProto.newBuilder()
            .setPassword("password123456677")
            .setEmail("null@test.com")
            .build();

    Exception exception = assertThrows(Exception.class, () -> service.create(payload));
    assertNotNull(exception);
  }
  @Test
  @DisplayName("E:  Null email ")
  public void e_getAll_nullEmail() {

    Sep3.UserProto payload = Sep3.UserProto.newBuilder()
            .setUsername("testUserWithoutEmail")
            .setPassword("password123456677")
            .build();

    Exception exception = assertThrows(Exception.class, () -> service.create(payload));
    assertNotNull(exception);
  }

  @Test
  @DisplayName("E:  Null Password ")
  public void e_getAll_nullPassword() {

    Sep3.UserProto payload = Sep3.UserProto.newBuilder()
            .setUsername("testUserWithoutEmail")
            .setEmail("email@tester.com")
            .build();

    Exception exception = assertThrows(Exception.class, () -> service.create(payload));
    assertNotNull(exception);
  }

  @Test
  @DisplayName("E: with null user object in list throws NullPointerException")
  void e_getAll_nullUserObject() {
    List<User> users = new ArrayList<>();
    users.add(testUser(1, "userTest1", "user1@test.com", 100));
    users.add(null);
    users.add(testUser(3, "userTest3", "user3@test.com", 300));
    when(userRepository.findAll()).thenReturn(users);
    NullPointerException exception = assertThrows(NullPointerException.class,
            () -> service.getAll());
    assertNotNull(exception);
  }

}
