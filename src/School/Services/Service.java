package School.Services;

import java.util.Scanner;
import School.School;

public abstract class Service {
    protected static Scanner user;
    protected School school;
    protected boolean finishService;

    public Service() {
        finishService = false;
    }

    abstract public void showActions();

    abstract public void actions();

    abstract public void startService();

}
