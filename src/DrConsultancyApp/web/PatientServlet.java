package DrConsultancyApp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DrConsultancyApp.dao.PatientDAO;
import DrConsultancyApp.model.Patient;

/**
 * Servlet implementation class PatientServlet

 */
@WebServlet("/")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientDAO patientDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServlet() {
        this.patientDAO= new PatientDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        try {
        /*	if(action.equals("/new")) {
        		showNewForm(request, response);
        	}else if(action.equals("/insert")) {
        		insertPatient(request, response);
        	}else if(action.equals("/delete")) {
        		deletePatient(request, response);
        	}else if(action.equals("/edit")) {
        		showEditForm(request, response);
        	}else if(action.equals("/update")) {
        		updatePatient(request, response);
        	}else
        		 listPatient(request, response);*/
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                	insertPatient(request, response);
                    break;
                case "/delete":
                    deletePatient(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatePatient(request, response);
                    break;
                default:
                    listPatient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	private void listPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        List <Patient> listUser = patientDAO.selectAllPatients();
        request.setAttribute("listPatient", listUser);//check heeeerreee................
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
		
	}

	private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String description = request.getParameter("description");

        Patient patient = new Patient(id, name, email, description);
        patientDAO.updatePatient(patient);
        response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int id = Integer.parseInt(request.getParameter("id"));
        Patient existingPatient = patientDAO.selectPatient(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        request.setAttribute("patient", existingPatient);
        dispatcher.forward(request, response);
		
	}

	private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		// TODO Auto-generated method stub
        int id = Integer.parseInt(request.getParameter("id"));
        patientDAO.deletePatient(id);
        response.sendRedirect("list");
		
	}

	private void insertPatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String description = request.getParameter("description");
        Patient newPatient = new Patient(name, email, description);
        patientDAO.insertPatient(newPatient);
        response.sendRedirect("list");
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
