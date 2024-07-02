package com.redbus.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.redbus.bean.output.AllBookings;
import com.redbus.bean.output.AllBuses;
import com.redbus.bean.output.BusesData;
import com.redbus.bean.output.TicketData;
import com.redbus.bean.pojo.BoardingPoint;
import com.redbus.bean.pojo.BookingsData;
import com.redbus.bean.pojo.Bus;
import com.redbus.bean.pojo.BusInfo;
import com.redbus.bean.pojo.BusesImage;
import com.redbus.bean.pojo.DroppingPoint;
import com.redbus.bean.pojo.User;

public class CommonDao {
	public long registerUser(User user){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(user);
		
		transaction.commit();
		session.close();
		sf.close();
		return user.getUserId();
	}
	
	public User verifyUser(String email){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "FROM User WHERE email=:email";
		Query query = session.createQuery(hql);
        query.setParameter("email", email);

        // Execute query and get single result or null
        User user = (User)query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close();
		return user;
	}
	
	public Bus getBusById(long busId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "FROM Bus WHERE busId=:busId";
		Query query = session.createQuery(hql);
        query.setParameter("busId", busId);

        // Execute query and get single result or null
        Bus bus = (Bus)query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close();
		return bus;
	}
	
	public DroppingPoint getDroppingPointById(long droppingPoint){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "FROM DroppingPoint WHERE id=:droppingPoint";
		Query query = session.createQuery(hql);
        query.setParameter("droppingPoint", droppingPoint);

        // Execute query and get single result or null
        DroppingPoint droppingpoint = (DroppingPoint)query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close();
		return droppingpoint;
	}
	
	public BoardingPoint getBoardingPointById(long boardingPoint){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "FROM BoardingPoint WHERE id=:boardingPoint";
		Query query = session.createQuery(hql);
        query.setParameter("boardingPoint", boardingPoint);

        // Execute query and get single result or null
        BoardingPoint boardingpoint = (BoardingPoint)query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close();
		return boardingpoint;
	}
	
	public long addBus(Bus bus){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(bus);
		
		transaction.commit();
		session.close();
		sf.close();
		return bus.getBusId();
	}
	
	public long addBusInfo(BusInfo busInfo){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(busInfo);
		
		transaction.commit();
		session.close();
		sf.close();
		return busInfo.getId();
	}
	
	public List<BusesData> getBusesData(String startPoint,String endPoint){
		List<BusesData> busesData = new ArrayList<>();
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "SELECT b, bi " + "FROM Bus b " + "JOIN b.busInfos bi " +
                "WHERE b.isActive = 'Y' " + "AND b.busFrom = :busFrom " + "AND b.busTo = :busTo";
   
		Query query = session.createQuery(hql);
		query.setParameter("busFrom", startPoint);
		query.setParameter("busTo", endPoint);
		
		List<Object[]> results = query.list();
		
		for (Object[] result : results) {
		    Bus bus = (Bus) result[0];
		    BusInfo businfo = (BusInfo) result[1];
		    
		    BusesData busesdata = new BusesData();
		    
		    busesdata.setBusId(bus.getBusId());
		    busesdata.setBusName(bus.getBusName());
		    busesdata.setRoute(bus.getRoute());
		    busesdata.setStartingPoint(bus.getBusFrom());
		    busesdata.setEndingPoint(bus.getBusTo());
		    busesdata.setStartTime(bus.getStartTime());
		    busesdata.setEndTime(bus.getEndTime());
		    busesdata.setBusType(businfo.getBusType());
		    busesdata.setBusRegistrationNumber(businfo.getBusRegistationNumber());
		    busesdata.setIsPrimo(businfo.getIsPrimo());
		    busesdata.setTicketPrice(businfo.getTicketPrice());
		    busesdata.setTotalSeats(businfo.getTotalSeats());
		    
		    busesData.add(busesdata);
		}
		
		transaction.commit();
		session.close();
		sf.close();
		return busesData;
	}
	
	public List<User> getAllUsers(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from User WHERE userType=0");  
		List<User> list = query.list(); 
		transaction.commit();
		session.close();
		sf.close(); 
		
		return list;
	}
	
	public List<AllBuses> getAllBuses(){
		List<AllBuses> busesData = new ArrayList<>();
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "SELECT b, bi " + "FROM Bus b " + "JOIN b.busInfos bi ";
   
		Query query = session.createQuery(hql);
		
		List<Object[]> results = query.list();
		
		for (Object[] result : results) {
		    Bus bus = (Bus) result[0];
		    BusInfo businfo = (BusInfo) result[1];
		    
		    AllBuses busesdata = new AllBuses();
		    
		    busesdata.setBusId(bus.getBusId());
		    busesdata.setBusName(bus.getBusName());
		    busesdata.setRoute(bus.getRoute());
		    busesdata.setStartingPoint(bus.getBusFrom());
		    busesdata.setEndingPoint(bus.getBusTo());
		    busesdata.setStartTime(bus.getStartTime());
		    busesdata.setEndTime(bus.getEndTime());
		    busesdata.setBusType(businfo.getBusType());
		    busesdata.setBusRegistrationNumber(businfo.getBusRegistationNumber());
		    busesdata.setIsPrimo(businfo.getIsPrimo());
		    busesdata.setIsActive(bus.getIsActive());
		    busesdata.setTicketPrice(businfo.getTicketPrice());
		    busesdata.setTotalSeats(businfo.getTotalSeats());
		    busesdata.setCreatedAt(bus.getCreatedAt());
		    
		    busesData.add(busesdata);
		}
		
		transaction.commit();
		session.close();
		sf.close();
		return busesData;
	}
	
	public long uploadBusImage(BusesImage busimage){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(busimage);
		
		transaction.commit();
		session.close();
		sf.close();
		return busimage.getId();
	}
	
	public List<BusesImage> getBusImages(long busId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from BusesImage WHERE busId=:busId"); 
		query.setParameter("busId", busId);
		List<BusesImage> list = query.list(); 
		transaction.commit();
		session.close();
		sf.close(); 
		
		return list;
	}
	
	public long addPoints(BoardingPoint point){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(point);
		
		transaction.commit();
		session.close();
		sf.close();
		return point.getId();
	}
	
	public long addDroppingPoints(DroppingPoint point){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(point);
		
		transaction.commit();
		session.close();
		sf.close();
		return point.getId();
	}
	
	public List<BoardingPoint> getBoardingPoints(long busId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from BoardingPoint WHERE busId=:busId"); 
		query.setParameter("busId", busId);
		List<BoardingPoint> list = query.list(); 
		transaction.commit();
		session.close();
		sf.close(); 
		
		return list;
	}
	
	public List<DroppingPoint> getDestinationPoints(long busId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from DroppingPoint WHERE busId=:busId"); 
		query.setParameter("busId", busId);
		List<DroppingPoint> list = query.list(); 
		transaction.commit();
		session.close();
		sf.close(); 
		
		return list;
	}
	
	public long bookTicket(BookingsData booking){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(booking);
		
		transaction.commit();
		session.close();
		sf.close();
		return booking.getBookingId();
	}
	
	public List<TicketData> getUserTickets(long userId){
		List<TicketData> userTickets = new ArrayList<>();
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "SELECT bd, b, bi, bp, dp, u " +
	             "FROM BookingsData bd " +
	             "JOIN bd.bus b " +
	             "JOIN b.busInfos bi " +
	             "JOIN bd.boardingPoint bp " +
	             "JOIN bd.droppingPoint dp " +
	             "JOIN bd.user u " +
	             "WHERE u.userId = :userId";
   
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		
		List<Object[]> results = query.list();
		
		for (Object[] result : results) {
			BookingsData bd = (BookingsData) result[0];
            Bus b = (Bus) result[1];
            BusInfo bi = (BusInfo) result[2];
            BoardingPoint bp = (BoardingPoint) result[3];
            DroppingPoint dp = (DroppingPoint) result[4];
		    
            TicketData usertickets = new TicketData();
		    
            usertickets.setBookingId(bd.getBookingId());
            usertickets.setBusName(b.getBusName());
            usertickets.setBoardingPoint(bp.getBoardingPoint());
            usertickets.setDestinationPoint(dp.getDroppingPoint());
            usertickets.setIsPrimo(bi.getIsPrimo());
            usertickets.setBusRegistrationNo(bi.getBusRegistationNumber());
            usertickets.setBusType(bi.getBusType());
            usertickets.setIsActive(bd.getIsActive());
            usertickets.setTicketPrice(bd.getTotalPrice());
            usertickets.setSeats(bd.getSeats());
		    
            userTickets.add(usertickets);
		}
		
		transaction.commit();
		session.close();
		sf.close();
		return userTickets;
	}
	
	public long cancelTicket(long bookingId){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("UPDATE BookingsData SET isActive='N' WHERE bookingId=:bookingId"); 
		query.setParameter("bookingId", bookingId);
		
		long nums = query.executeUpdate(); 
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return nums;
	}
	
	public long changeBusStatus(long busId,String busStatus){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("UPDATE Bus SET isActive=:busStatus WHERE busId=:busId"); 
		query.setParameter("busStatus", busStatus);
		query.setParameter("busId", busId);
		
		long nums = query.executeUpdate(); 
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return nums;
	}
	
	public long changeUserStatus(long userId,String userStatus){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("UPDATE User SET isActive=:userStatus WHERE userId=:userId"); 
		query.setParameter("userStatus", userStatus);
		query.setParameter("userId", userId);
		
		long nums = query.executeUpdate(); 
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return nums;
	}
	
	public List<AllBookings> getAllBookings(){
		List<AllBookings> allBookings = new ArrayList<>();
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		String hql = "SELECT bd, b, bi, bp, dp, u " +
	             "FROM BookingsData bd " +
	             "JOIN bd.bus b " +
	             "JOIN b.busInfos bi " +
	             "JOIN bd.boardingPoint bp " +
	             "JOIN bd.droppingPoint dp " +
	             "JOIN bd.user u ";
   
		Query query = session.createQuery(hql);
		
		List<Object[]> results = query.list();
		
		for (Object[] result : results) {
			BookingsData bd = (BookingsData) result[0];
            Bus b = (Bus) result[1];
            BusInfo bi = (BusInfo) result[2];
            BoardingPoint bp = (BoardingPoint) result[3];
            DroppingPoint dp = (DroppingPoint) result[4];
            User u = (User) result[5];
		    
            AllBookings bookings = new AllBookings();
		    
            bookings.setBookingId(bd.getBookingId());
            bookings.setBusName(b.getBusName());
            bookings.setBoardingPoint(bp.getBoardingPoint());
            bookings.setDroppingPoint(dp.getDroppingPoint());
            bookings.setIsPrimo(bi.getIsPrimo());
            bookings.setBusRegistrationNo(bi.getBusRegistationNumber());
            bookings.setBusType(bi.getBusType());
            bookings.setIsActive(bd.getIsActive());
            bookings.setTotalPrice(bd.getTotalPrice());
            bookings.setBookedSeats(bd.getSeats());
            bookings.setBookingDate(bd.getBookingDate());
            bookings.setGender(bd.getGender());
            bookings.setCreatedAt(bd.getCreatedAt());
            bookings.setRoute(b.getRoute());
            bookings.setName(u.getName());
            bookings.setMobileNumber(u.getMobileNumber());
            bookings.setEmail(u.getEmail());
		    
            allBookings.add(bookings);
		}
		
		transaction.commit();
		session.close();
		sf.close();
		return allBookings;
	}
	
	public long changeBookingStatus(long bookingId,String bookingStatus){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("UPDATE BookingsData SET isActive=:bookingStatus WHERE bookingId=:bookingId"); 
		query.setParameter("bookingStatus", bookingStatus);
		query.setParameter("bookingId", bookingId);
		
		long nums = query.executeUpdate(); 
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return nums;
	}
	
	public long getTotalActiveUsersCount(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE isActive='Y' AND userType=0"); 
		
		long count = (Long) query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return count;
	}
	
	public long getTotalActiveBusesCount(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("SELECT COUNT(b) FROM Bus b WHERE isActive='Y'"); 
		
		long count = (Long) query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return count;
	}
	
	public long getTotalActiveBookingsCount(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("SELECT COUNT(bd) FROM BookingsData bd WHERE isActive='Y'"); 
		
		long count = (Long) query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return count;
	}
	
	public long getTotalCancelledBookingsCount(){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("SELECT COUNT(bd) FROM BookingsData bd WHERE isActive='N'"); 
		
		long count = (Long) query.uniqueResult();
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return count;
	}
	
	public List<BookingsData> getBookingData(long busId,Date searchedDate){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
   
		Query query = session.createQuery("SELECT bd FROM BookingsData bd WHERE busId=:busId AND bookingDate=:bookingDate AND isActive='Y'");
		query.setParameter("busId", busId);
		query.setParameter("bookingDate", searchedDate);
		
		List<BookingsData> results = query.list();
		
		transaction.commit();
		session.close();
		sf.close();
		return results;
	}
	
	public long updateUserProfile(long userId,String path,String firstName,String lastName,String email,long mobileNumber){
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("UPDATE User SET name=:name,email=:email,mobileNumber=:mobileNumber,userProfile=:path WHERE userId=:userId"); 
		query.setParameter("name", firstName+" "+lastName);
		query.setParameter("email", email);
		query.setParameter("mobileNumber", mobileNumber);
		query.setParameter("path", path);
		query.setParameter("userId", userId);
		
		long nums = query.executeUpdate(); 
		
		transaction.commit();
		session.close();
		sf.close(); 
		
		return nums;
	}
}
