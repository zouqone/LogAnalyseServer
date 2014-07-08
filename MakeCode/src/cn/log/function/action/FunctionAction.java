/**
 * 
 */
package cn.log.function.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.log.function.service.IFunctionService;
import cn.log.function.vo.FunctionVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

/**
 * @author zouqone
 * @see FunctionAction
 */
public class FunctionAction extends AbstractBaseAction implements
		ModelDriven<FunctionVo> {

	public FunctionVo functionVo;
	public IFunctionService functionService;


	@Override
	public FunctionVo getModel() {
		// TODO Auto-generated method stub
		if(functionVo == null){
			functionVo = new FunctionVo();
		}
		return functionVo;
	}

	
	/**
	 * @return the functionVo
	 */
	public FunctionVo getFunctionVo() {
		return functionVo;
	}


	/**
	 * @param functionVo the functionVo to set
	 */
	public void setFunctionVo(FunctionVo functionVo) {
		this.functionVo = functionVo;
	}


	/**
	 * @return the functionService
	 */
	public IFunctionService getFunctionService() {
		return functionService;
	}


	/**
	 * @param functionService the functionService to set
	 */
	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}


	// 查询操作
	public void selectFunction() {
			
		request.setAttribute("FunctionList", functionService.getAllFunctionVo());
	}

	/**
	 * 查询所有的子节点
	 */
	public void getFunctionByParentCode(){
		String pCode=request.getParameter("pCode");
		String jsonString=functionService.queryFunctionByParentCode(pCode);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 查询所有的子节点
	 */
	public void getCheckTreeNodeByParentCode(){
		String pCode=request.getParameter("pCode");
		String jsonString=functionService.queryCheckFunctionByParentCode(pCode);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	public void queryFunction() {
			
		response.setContentType("text/html; charset=utf-8");

		String real = request.getRealPath("/");

		// 输出流
		PrintWriter out = null;
		String outInfo = "";

		List<FunctionVo> functionVoList = functionService.getAllFunctionVo();
		JSONArray jsonArray = JSONArray.fromObject(functionVoList);

		outInfo = jsonArray.toString();

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 按条件查询出所有的记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void queryFunctionByCondition() {
			
		response.setContentType("text/html; charset=utf-8");

		String condition = (String) request.getParameter("query");
		JSONObject jsonObject = JSONObject.fromObject(condition);
		FunctionVo functionVo = (FunctionVo) JSONObject.toBean(jsonObject,
				FunctionVo.class);

		// 输出流
		PrintWriter out = null;
		String outInfo = "";

		List<FunctionVo> functionVoList = functionService
				.getFunctionVoByCondition(functionVo);
		JSONArray jsonArray = JSONArray.fromObject(functionVoList);

		outInfo = jsonArray.toString();

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 新增Function
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void addFunction() {
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String condition = (String) request.getParameter("node");
		JSONObject jsonObject = JSONObject.fromObject(condition);
		FunctionVo functionVo = (FunctionVo) JSONObject.toBean(jsonObject,
				FunctionVo.class);

		// 输出流
		PrintWriter out = null;
		String outInfo = "";
		String id = null;
		id = functionService.insertFunctionVo(functionVo);
		outInfo = "{id:'" + id + "'}";

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 新增Function
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void EditFunction() {
		response.setContentType("text/html; charset=utf-8");

		String condition = (String) request.getParameter("node");
		JSONObject jsonObject = JSONObject.fromObject(condition);
		FunctionVo functionVo = (FunctionVo) JSONObject.toBean(jsonObject,
				FunctionVo.class);

		// 输出流
		PrintWriter out = null;
		String outInfo = "";
		// String id = null;
		Boolean flag = functionService.updateFunctionVo(functionVo);
		outInfo = "{id:'" + flag + "'}";

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void delFunction() {
		response.setContentType("text/html; charset=utf-8");

		String ids = (String) request.getParameter("ids");
		String[] idArray = ids.split(",");
		// 删除对象
		if (idArray != null && idArray.length > 0) {
			for (String id : idArray) {
				functionService.deleteFunctionVo(Integer.parseInt(id));
			}
		}

		// 输出流
		PrintWriter out = null;
		String outInfo = "";

		List<FunctionVo> functionVoList = functionService.getAllFunctionVo();
		JSONArray jsonArray = JSONArray.fromObject(functionVoList);

		outInfo = jsonArray.toString();

		// 向前台发送信息
		try {
			out = response.getWriter();
			out.print(outInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}


}
