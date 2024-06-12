import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.VelocityCalculation;
import models.VelocityRequestObject;
import models.VelocityResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;
/**
 * Servlet implementation class CalcAPI
 */
@WebServlet("/CalcAPI")
public class CalcAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcAPI() {
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//parse the request body with recursive method
		
		StringBuilder bodyReader = new StringBuilder();
		
		ReadRequestBody(request.getReader(), bodyReader);
		
		String data = bodyReader.toString();
		
		
		//deserialise the JSON from the js into a java object
		
		Gson gson = new Gson();
		
		VelocityRequestObject requestobj = gson.fromJson(data, VelocityRequestObject.class);

		
		
		
		double distance = requestobj.GetDistance();
		
		double time = requestobj.GetTime();
		
		VelocityCalculation calc = new VelocityCalculation();
		
		double velocity = VelocityCalculation.velocalc(distance, time);
		//invoke velocity utils to perform the calculation
		
		VelocityResponse velocityresponse = new VelocityResponse(velocity); 
		
		
		//turn the calculation back in to json data and send it back to the front end.
		String jsonReturnresponse = gson.toJson(velocityresponse);
		
		
		response.getWriter().write(jsonReturnresponse);
		response.getWriter().flush();
		
		
	
		
		
		
		
		
		
	}
	
	//recursive method to read the requst body
	
	private void ReadRequestBody(BufferedReader reader, StringBuilder bodyReader) {
		
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (line!=null) {
			bodyReader.append(line);
			ReadRequestBody(reader, bodyReader); //recuritve call on the read request body method
		}
		
	}
	
	

}
