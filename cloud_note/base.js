/**
 * 
 */
var path ="http://localhost:8080/cloud_note";
/*
 * 根据用户的ID加载笔记本和笔记
 */
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
         }
        if (c.indexOf(name)  == 0) {
            return c.substring(name.length, c.length);
         }
    }
    return "";
};
function loadUserBooks(){
	//1.获取用户的cookie中的user_id
	var user_id = getCookie("user_id");
	//2.判断得到的cookie中是否含有有效的ID
	if(user_id == null && user_id != ""){
		window.location.href(path+"/user/tologin.do");
	}
	else{
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"user_id":user_id},
			dataType:"json",
			success:function(result){
				if(result.status != 0){
					alert(result.msg);
				}
				//1为空，2为查询失败，0为集合存在
				else if(result.status == 0){
					//获取笔记本的集合
					var books = result.data;
					var array= new Array();
					for(var i = 0; i <books.length;i++){
						var bookId = books[i].note_book_id;
						array[i] = bookId;
						var bookName = books[i].note_book_name;
						createDl(bookId,bookName);
					}
					loadNotes(array);
				}
			},
			error:function(){
				alert("笔记本加载失败！");
			}
		});
	}
};
function loadNotes(array){
	//1.得到cookie中的值
	var user_id = getCookie("user_id");
	//2.判断得到的cookie中是否含有有效的ID
	if(user_id == null && user_id != ""){
		window.location.href(path+"/user/tologin.do");
	}
	else{
		$.ajax({
			url:path+"/note/loadnotes.do",
			type:"post",
			data:{"array":JSON.stringify(array)},
			dataType:"json",
			success:function(result){
				for(var i = 0;i < array.length;i++){
					if(result[array[i]]["status"] == 0){
						for(var j = 0;j < result[array[i]]["data"].length;j++){
							createUl(i,result[array[i]]["data"][j]["note_id"],result[array[i]]["data"][j]["note_title"]);
						}
					}
				}
			},
			error:function(){
				alert("笔记加载失败！");
			}
		});
	}
};
function createUl(index,note_id,note_title){
	var sul = "";
	sul += '<ul>';
	sul += '<li><a  href="javascript:void(0)">';
	sul += note_title;
	sul += '</a></li>';
	sul += '</ul>';
	
	var $ul = $(sul);
	//$ul.find("a").attr("data-note_id",note_id);
	$ul.find("a").attr("id",note_id);
	$ul.find("a").attr("data-title",note_title);
	$("dl").eq(index).find("dd").append($ul);
};
//创建笔记本dl元素
function createDl(bookId,bookName){
	var sdl = "";
	sdl += '<dl id="menu-article">';
	sdl += '<dt><i class="Hui-iconfont">&#xe720;</i>';
	sdl += bookName;
	sdl += '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>';
	sdl += '<dd>';
	sdl += '</dd>';
	sdl += '</dl>';
	var $dl = $(sdl);
//	将bookid绑定到dl上；
	$dl.attr("data-bookId",bookId);
	$("#book_dl").append($dl);	
};


