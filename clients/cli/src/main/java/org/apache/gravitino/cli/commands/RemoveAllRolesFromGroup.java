package org.apache.gravitino.cli.commands;

import org.apache.gravitino.cli.ErrorMessages;
import org.apache.gravitino.client.GravitinoClient;
import org.apache.gravitino.exceptions.NoSuchGroupException;
import org.apache.gravitino.exceptions.NoSuchMetalakeException;
import org.apache.gravitino.exceptions.NoSuchRoleException;

import java.util.List;

public class RemoveAllRolesFromGroup extends Command {

    protected String metalake;
    protected String group;

    /**
     * Gets all roles from a group and removes them.
     *
     * @param url The URL of the Gravitino server.
     * @param ignoreVersions If true don't check the client/server versions match.
     * @param metalake The name of the metalake.
     * @param group The name of the group.
     */
    public RemoveAllRolesFromGroup(
            String url, boolean ignoreVersions, String metalake, String group) {
        super(url, ignoreVersions);
        this.metalake = metalake;
        this.group = group;
    }

    /** Gets all roles from a group and removes them. */
    @Override
    public void handle() {
        List<String> roles = null;

        try {
            GravitinoClient client = buildClient(metalake);
            roles = client.getGroup(group).roles();
            if(!roles.isEmpty()) {
                client.revokeRolesFromGroup(roles, group);
            }
        } catch (NoSuchMetalakeException err) {
            System.err.println(ErrorMessages.UNKNOWN_METALAKE);
            return;
        } catch (NoSuchRoleException err) {
            System.err.println(ErrorMessages.UNKNOWN_ROLE);
            return;
        } catch (NoSuchGroupException err) {
            System.err.println(ErrorMessages.UNKNOWN_GROUP);
            return;
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
            return;
        }

        if (!roles.isEmpty()) {
            String all = String.join(",", roles);
            System.out.println(all + " removed from " + group);
        } else {
            System.out.println("No roles associated with " + group);
        }
    }
}
