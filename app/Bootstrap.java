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

        u.merge();



    }




}