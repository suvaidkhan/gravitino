package org.apache.gravitino.cli.commands;

import org.apache.gravitino.cli.ErrorMessages;
import org.apache.gravitino.client.GravitinoClient;
import org.apache.gravitino.exceptions.NoSuchMetalakeException;
import org.apache.gravitino.exceptions.NoSuchRoleException;
import org.apache.gravitino.exceptions.NoSuchUserException;

import java.util.List;

public class RemoveAllRolesFromUser extends Command {

  protected String metalake;
  protected String user;

  /**
   * Removes all role from a user.
   *
   * @param url The URL of the Gravitino server.
   * @param ignoreVersions If true don't check the client/server versions match.
   * @param metalake The name of the metalake.
   * @param user The name of the user.
   */
  public RemoveAllRolesFromUser(String url, boolean ignoreVersions, String metalake, String user) {
    super(url, ignoreVersions);
    this.metalake = metalake;
    this.user = user;
  }
  /** Gets roles from a user and removes them. */
  @Override
  public void handle() {
    List<String> roles = null;
    try {
      GravitinoClient client = buildClient(metalake);
      roles = client.getUser(user).roles();
      if (!roles.isEmpty()) {
        client.revokeRolesFromUser(roles, user);
      }
    } catch (NoSuchMetalakeException err) {
      System.err.println(ErrorMessages.UNKNOWN_METALAKE);
      return;
    } catch (NoSuchUserException err) {
      System.err.println(ErrorMessages.UNKNOWN_USER);
      return;
    } catch (NoSuchRoleException err) {
      System.err.println(ErrorMessages.UNKNOWN_ROLE);
      return;
    } catch (Exception exp) {
      System.err.println(exp.getMessage());
      return;
    }

    if (!roles.isEmpty()) {
      String all = String.join(",", roles);
      System.out.println(all + " removed from " + user);
    } else {
      System.out.println("No roles associated with the user " + user);
    }
  }
}
