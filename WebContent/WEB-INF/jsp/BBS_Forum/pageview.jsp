<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id=PageSelectorBar>
			<div id=PageSelectorMemo>
				页次：${superpage.pageNo }/${superpage.pageCount }页 &nbsp;
				每页显示：${superpage.pageNum }条 &nbsp;
				总记录数：${superpage.pageSum }条
			</div>
			<div id=PageSelectorSelectorArea>
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
				-->
				<a onclick="gotoPageNum(1)" title="首页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
				
				<c:forEach begin="1" step="1" end="${superpage.pageCount }" var="i">
				<c:if test="${i==superpage.pageNo }">
					<span class="PageSelectorNum PageSelectorSelected">${i}</span>
				</c:if>
				<c:if test="${i!=superpage.pageNo }">
					<span class="PageSelectorNum" style="cursor: hand;" onClick="pageRequest(${i});">${i}</span>
				</c:if>
				</c:forEach>
				<a onclick="gotoPageNum(${superpage.pageCount})" title="尾页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>

				转到：
				<select name="pageNo" id="pageNo"> 
					<c:forEach begin="1" step="1" end="${superpage.pageCount }" var="i">
						<option value="${i}">${i}</option> 
					</c:forEach>
				</select> 
			</div>
		</div>
		
		<script type="text/javascript">
			function gotoPageNum(pageNum){
				//动态添加一个隐藏输入框，传递页码
				$("#pageForm").append('<input type="hidden" value="'+pageNum+'" name="currentPage"/>');
				$("#pageForm").submit();
			}
		</script>
