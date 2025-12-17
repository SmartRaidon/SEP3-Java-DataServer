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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest @TestInstance(TestInstance.Lifecycle.PER_CLASS) class Sep3JavaDataServerApplicationTests
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
  @DisplayName("Z: getAll() with zero users returns empty list")
  public void z_getAll_zero() {
    when(userRepository.findAll()).thenReturn(List.of());
    Sep3.UserListProto out = service.getAll();
    assertEquals(0, out.getUsersCount());
  }

  @Test
  @DisplayName("O: getAll() with one user returns exactly one user")
  public void o_getAll_one() {
    User u = testUser(1, "oneUserTest", "one@x.com", 77);
    when(userRepository.findAll()).thenReturn(List.of(u));
    Sep3.UserListProto out = service.getAll();
    assertEquals(1, out.getUsersCount());
    assertEquals(77, out.getUsers(0).getPoints());
    assertEquals("oneUserTest", out.getUsers(0).getUsername());
  }

  @Test
  @DisplayName("M: getAll() with many users returns all users correctly")
  public void m_getAll_many() {
    List<User> users = new ArrayList<>();
    users.add(testUser(1, "userBlackBoxTest1", "user1@test.com", 100));
    users.add(testUser(2, "userBlackBoxTest12", "user2@test.com", 200));
    users.add(testUser(3, "userBlackBoxTest13", "user3@test.com", 300));
    users.add(testUser(4, "userBlackBoxTest14", "user4@test.com", 400));
    users.add(testUser(5, "userBlackBoxTest15", "user5@test.com", 500));

    when(userRepository.findAll()).thenReturn(users);
    Sep3.UserListProto out = service.getAll();

    assertEquals(5, out.getUsersCount());
    assertThat(out.getUsersList())
            .extracting(Sep3.UserProto::getUsername)
            .containsExactlyInAnyOrder(
                    "userBlackBoxTest1",
                    "userBlackBoxTest12",
                    "userBlackBoxTest13",
                    "userBlackBoxTest14",
                    "userBlackBoxTest15"
            );
  }

  @Test
  @DisplayName("B: getAll() with different payload sizes - 1, 10, 100, 1000 users")
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
  @DisplayName("E: getAll() with all users having null points ")
  void e_getAll_allNullPoints() {
    List<User> users = new ArrayList<>();
    users.add(testUser(1, "nulluser1", "null1@test.com", null));
    users.add(testUser(2, "nulluser2", "null2@test.com", null));
    users.add(testUser(3, "nulluser3", "null3@test.com", null));

    when(userRepository.findAll()).thenReturn(users);
    Sep3.UserListProto result = service.getAll();

    assertEquals(3, result.getUsersCount());
    for (int i = 0; i < 3; i++) {
      assertEquals(0, result.getUsers(i).getPoints());
    }
  }



}
