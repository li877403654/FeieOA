<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>配置权限</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script src="${pageContext.request.contextPath}/script/pageCommon.js"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/script/PageUtils.js"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/script/DemoData.js"
	charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/script/DataShowManager.js"
	charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
<script type="text/javascript">
	$(function(){
		$("#privilegeTree").treeview();
		$('input[name=privilegeIds]').click(function(){
		//当选中某个权限时，同时选中其下级权限，当取消某个权限时取消其下级权限
			$(this).siblings('ul').find('input').attr('checked',this.checked);
			if (this.checked) {
				$(this).parents('li').children('input').attr('checked',true);
			}else{
				var size = $(this).parent('li').siblings('li').children('input:checked').size();
				if (size==0) {
					$(this).parent().parent().siblings('input').attr('checked',false);
				}
			}
			
		});
		
	});
</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="post_setPrivilege.action">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${post.pname }】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="CHECKBOX" id="cbSelectAll" onclick="$('input[name=privilegeIds]').attr('checked',this.checked)" />
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
							<input type="hidden" name="pid" value="${post.pid }">
								<ul id="privilegeTree">
									<c:forEach items="${privileges}" varStatus="i" step="1" begin="0" var="p1">
										<li>
											<input id="cd_${p1.id }" type="checkbox" <c:forEach items="${post.privileges }" var="p"><c:if test="${p.id==p1.id }">checked="checked"</c:if></c:forEach> name="privilegeIds" value="${p1.id}"/>
											<label for='cd_${p1.id }'><span class="folder" id="${p1.id }"></span>${p1.name}</label>
											<ul>
												<c:forEach items="${p1.parent}" varStatus="i" step="1" begin="0" var="p2">
													<li>
														<input id="cd_${p2.id }" type="checkbox" <c:forEach items="${post.privileges }" var="p"><c:if test="${p.id==p2.id }">checked="checked"</c:if></c:forEach> name="privilegeIds" value="${p2.id}"/>
														<label for='cd_${p2.id }'><span class="folder" id="${p2.id }"></span>${p2.name}</label>
														<ul>
															<c:forEach items="${p2.parent}" varStatus="i" step="1" begin="0" var="p3">
																<li>
																	<input id="cd_${p3.id }" type="checkbox" <c:forEach items="${post.privileges }" var="p"><c:if test="${p.id==p3.id }">checked="checked"</c:if></c:forEach> name="privilegeIds" value="${p3.id}"/>											
																	<label for='cd_${p3.id }'><span class="folder" id="${p3.id }"></span>${p3.name}</label>
																</li>
															</c:forEach>
														</ul>
													</li>
												</c:forEach>
											</ul>
										</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
	说明：<br />
	1，选中一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>

</body>
</html>
    