import models.Role;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {



    @Override
    public void doJob() throws Exception {


        User u = new User();
        u.email = "kamal.azaddyne@gmail.com";
        u.fullname = "admin";
        u.firstname = "azaddyne";
        u.lastname = "kamal";
        u.password = "admin";
        u.isAdmin = true;
        u.roleId = 1L;

        u.save();

        System.out.println("####---- "+u.password);

        Role r = new Role();
        r.roleName = "Sales";
        r.roleDescription = "main manager";
        r.parentRole = 0L;

        r.save();

        Role r2 = new Role();
        r2.roleName = "Purchase";
        r2.roleDescription = "main manager";
        r2.parentRole = 0L;

        r2.save();



    }




}