<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>导航菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="JavaScript" src="script/jquery.js"></script>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
<script type="text/javascript">
	$(function(){
	});

</script>
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <c:forEach items="${privileges}" var="pri">
        	<c:forEach items="${MyPri}" var="mypri">
	        	<c:if test="${pri.id==mypri.id}">
		        	<li class="level1">
		            <div onClick="menuClick(this)" class="level1Style"><img src="style/images/MenuIcon/${pri.id}.gif" class="Icon" />${pri.name }</div>
		            <ul style="display: none;" class="MenuLevel2">
		            	<c:forEach items="${pri.parent}" var="chi">
		            		<c:forEach items="${MyPri}" var="mypri">
			            		<c:if test="${chi.id==mypri.id}">
				                <li class="level2">
				                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="${chi.url}">${chi.name}</a></div>
			    	            </li>
			    	            </c:if>
		    	            </c:forEach>
		            	</c:forEach>
		            </ul>
		        </li>
	        	</c:if>
        	</c:forEach>
        </c:forEach>
        
      </ul>
<!--       <ul id="MenuUl"> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this)" class="level1Style"><img src="style/images/MenuIcon/FUNC20001.gif" class="Icon" />个人办公</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a href="">个人考勤</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a href="">日程安排</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a href="">工作计划</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a href="" >工作日记</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                 	<div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a href="">通讯录</a></div> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20057.gif" class="Icon" /> 审批流转</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="Flow_ProcessDefinition/list.html">审批流程管理</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="Flow_FormTemplate/list.html">表单模板管理</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" -->
<!-- 			href="Flow_Formflow/formTemplateList.html">起草申请</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="Flow_Formflow/myTaskList.html"> 待我审批</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" -->
<!-- 			href="Flow_FormFlow_Old/mySubmittedList.html">我的申请查询</a></div> -->
<!--                 </li> -->
<!--                
<!-- 		<li class="level2"> -->
<!-- 			<div class="level2Style"><img -->
<!--                   src="style/images/MenuIcon/menu_arrow_single.gif" /> <a  target="right" href="Flow_FormQuery/myApprovedList.html">经我审批</a> </div> -->
<!-- 		</li> -->
<!-- 		-->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20064.gif" class="Icon" /> 网上交流</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div onClick="subMenuClick(this);" class="level2Style"><img src="style/images/MenuIcon/menu_arrow_close.gif" /> 短消息</div> -->
<!--                     <ul style="display: none;" class="MenuLevel2"> -->
<!--                         <li class="level3Head"><a target="right" href="Person_Message/saveUI.html">发送短消息</a></li> -->
<!--                         <li class="level33"><a target="right" href="Person_Message/inbox.html">已接收</a></li> -->
<!--                         <li class="level33"><a target="right" href="Person_Message/outbox.html">已发送</a></li> -->
<!--                         <li class="level32"><a target="right" href="Person_Message/draftbox.html">草稿箱</a></li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!-- 				<li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_out.action">发送邮件</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_go.action?uid=3&startnum=1">发件箱</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_come.action?uid=3&startnum=1">收信箱</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_draft.action?uid=3&startnum=1">草稿箱</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_waste.action?uid=3&startnum=1">回收站</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=outbox">发信箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=inbox">收信箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=draftbox">草稿箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=waste">垃圾箱</a></div> --%>
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--         	<div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20056.gif" class="Icon" /> 收发邮件</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=outbox">发信箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=inbox">收信箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=draftbox">草稿箱</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="email_box.action?uid=${user.uid }&startnum=1&box=waste">垃圾箱</a></div> --%>
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20070.gif" class="Icon" /> 综合行政</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 考勤管理</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div onClick="subMenuClick(this);" class="level2Style"><img src="style/images/MenuIcon/menu_arrow_close.gif" /> 会议管理</div> -->
<!--                     <ul style="display: none;" class="MenuLevel2"> -->
<!--                         <li class="level3Head">会议申请</li> -->
<!--                         <li class="level33">暂存会议查询</li> -->
<!--                         <li class="level33">待我参加会议</li> -->
<!--                         <li class="level33">我已参加会议</li> -->
<!--                         <li class="level33">新建会议纪要</li> -->
<!--                         <li class="level33">会议纪要查询</li> -->
<!--                         <li class="level33">待开会议查询</li> -->
<!--                         <li class="level33">已开会议查询</li> -->
<!--                         <li class="level33">会议室设置</li> -->
<!--                         <li class="level32">会议类型设置</li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div onClick="subMenuClick(this);" class="level2Style"><img src="style/images/MenuIcon/menu_arrow_close.gif" /> 车辆管理</div> -->
<!--                     <ul style="display: none;" class="MenuLevel2"> -->
<!--                         <li class="level3Head">用车申请</li> -->
<!--                         <li class="level33">派车管理</li> -->
<!--                         <li class="level33">我的申请记录</li> -->
<!--                         <li class="level33">车辆状态</li> -->
<!--                         <li class="level33">车辆档案</li> -->
<!--                         <li class="level32">私车公用</li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC261000.gif" class="Icon" /> 人力资源</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 档案管理</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 培训记录</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 奖惩记录</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 职位变更</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 人事合同</div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 类别维护</div> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20076.gif" class="Icon" /> 实用工具</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="_blank" href="http://www.itcast.cn">公司网站</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="_blank" href="http://qq.ip138.com/train/">火车时刻</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="_blank" href="http://www.airchina.com.cn/">飞机航班</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="_blank" href="http://www.ip138.com/post/">邮编/区号</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="_blank" href="http://www.timedate.cn/">国际时间</a></div> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20077.gif" class="Icon" /> 个人设置</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<%--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="user_user.action?uid=${user.uid}">个人信息</a></div> --%>
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="user_userUpsw.action">密码修改</a></div> -->
<!--                 </li> -->
<!--                <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 桌面定义</div> -->
<!--                 </li>-->
<!--             </ul> -->
<!--         </li> -->
<!--         <li class="level1"> -->
<!--             <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC20082.gif" class="Icon" /> 系统管理</div> -->
<!--             <ul style="display: none;" class="MenuLevel2"> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="post_list.action"> 职位管理</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="dept_allDept.action"> 部门管理</a></div> -->
<!--                 </li> -->
<!--                  <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="dept_getAllUser.action"> 部门人员管理</a></div> -->
<!--                 </li> -->
<!--                 <li class="level2"> -->
<!--                     <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="user_findUser.action"> 用户管理</a></div> -->
<!--                 </li> -->
              
<!--                 <li class="level2"> --> 
<!--                      <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 基础数据设置</div> --> 
<!--                </li> --> 
<!--             </ul> -->
<!--         </li> -->
<!--     </ul> -->
</div>
</body>
</html>
