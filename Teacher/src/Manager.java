import java.sql.SQLException;
import java.util.Random;


public class Manager {
	private Random r;
	private Data data;
	Manager() throws ClassNotFoundException, SQLException{
		data = new Data();
		data.intit();	
		r=new Random();
	}

	String [] getString() throws SQLException{
		int id=r.nextInt()% data.lenght;
		if(id<0)
			id=-id;
		return data.getJob(id);
	}
}
