<%@ page import="com.redwood.rp.core.vo.json.ErrorVO,com.redwood.rp.core.constant.ErrorDescription,org.codehaus.jackson.map.ObjectMapper,com.redwood.rp.core.util.ExceptionUtil,com.redwood.rp.core.vo.json.StatusVO,com.redwood.rp.core.vo.json.BaseErrorInfoResponseVO,com.redwood.rp.core.exception.ServiceException,com.redwood.rp.core.constant.ExceptionType"%>

<%   response.setContentType("application/json");   
ErrorVO errorVO = new ErrorVO(ErrorDescription.ERR_CD_COMMON_RESOURCE_NOTFOUND,ErrorDescription.ERR_MSG_COMMON_RESOURCE_NOTFOUND, ErrorDescription.ERR_MSG_COMMON_RESOURCE_NOTFOUND);   

StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(new ServiceException(ExceptionType.EXCEPTION_NOTFOUND,errorVO));   

BaseErrorInfoResponseVO errorResponse = new BaseErrorInfoResponseVO(status);   

ObjectMapper mapper = new ObjectMapper();   

String errorStr = mapper.writeValueAsString(errorResponse);   

out.print(errorStr);%>