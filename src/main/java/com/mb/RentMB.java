package com.mb;

import com.facade.RentFacade;
import com.google.common.base.Joiner;
import com.model.Flat;
import com.model.Rent;
import com.model.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Gruby on 03.05.2017.
 */
@ManagedBean
@ViewScoped
public class RentMB extends AbstractMB {

    private RentFacade rentFacade;
    private Rent rent;
    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;
    @ManagedProperty(value = "#{flatMB}")
    private FlatMB flatMB;
    private User user;
    private Flat flatToUpdate;
    private int flatId;
    private static final String QUOTE = "\"";

    public int getFlatId() {
        return flatId;
    }

    @PostConstruct
    public void pullFlatFromFlash() {
        flatToUpdate = (Flat) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("flat");
    }

    public Flat getFlatToUpdate() {
        if (flatToUpdate == null) {
            flatToUpdate = new Flat();
        }
        return flatToUpdate;
    }

    public void setFlatToUpdate(Flat flatToUpdate) {
        this.flatToUpdate = flatToUpdate;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public void setFlatMB(FlatMB flatMB) {
        this.flatMB = flatMB;
    }

    public FlatMB getFlatMB() {
        return this.flatMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public User getUser() {
        if (user == null) {
            this.user = userMB.getUser();
        }
        return user;
    }

    public RentFacade getRentFacade() {
        if (rentFacade == null) {
            rentFacade = new RentFacade();
        }
        return rentFacade;
    }

    public Rent getRent() {
        if (rent == null) {
            rent = new Rent();
        }
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void createRent() {
        try {
            int userId = getUser().getId();
            int flatId = flatToUpdate.getId();
            getRentFacade().addUserAndFlatToRent(flatId, userId, getRent());
            resetRent();
            displayInfoMessageToUserAfterRedirect("Flat booked successfully!");
        } catch (Exception e) {
            displayErrorMessageToUser("Oops we couldn't book flat for you");
            e.printStackTrace();
        }
    }

    public void resetRent() {
        this.rent = new Rent();
    }

    public String getBookedDays() {

        List<String> result = new ArrayList<>();
        List<Date> Entries = getBookedDates();
        for (Date entry : Entries) {
            addDayToResult(entry, result);
        }
        String joined = Joiner.on(",").join(result);
        return joined;
    }

    private void addDayToResult(Date date, List<String> result) {
        SimpleDateFormat sdf = new SimpleDateFormat("M-d-yyyy");
        result.add(QUOTE + sdf.format(date).toString() + QUOTE);
        System.out.println(result.toString());

    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startdate);

        Calendar c = Calendar.getInstance();
        c.setTime(enddate);
        c.add(Calendar.DATE, 1);

        while (calendar.getTime().before(c.getTime())) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public List<Date> getBookedDates() {

        List<Date> listOfBookedDays = new ArrayList<>();
        List<Rent> listOfRents = getFlatToUpdate().getRents();

        for (Rent rent : listOfRents) {
            Date startDate = rent.getStartDate();
            Date finishDate = rent.getFinishDate();
            listOfBookedDays.addAll(getDaysBetweenDates(startDate, finishDate));
        }
        return listOfBookedDays;
    }

    public Date getMinDate() {
        Date startOfRent = getFlatToUpdate().getStartDate();
        Date today = new Date();
        if (startOfRent.after(today)) {
            return startOfRent;
        }
        return today;
    }

    public Date getMaxDate() {
        Date endOfRent = getFlatToUpdate().getFinishDate();
        return endOfRent;

    }

    public Date getMinDateForFinish() {
        if (rent != null && rent.getStartDate() != null) {
            return rent.getStartDate();
        }
        return getMinDate();
    }


}
