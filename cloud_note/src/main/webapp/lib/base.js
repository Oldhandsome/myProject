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
function trash(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: path+url,
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);
            body.load(path+"/trash.html");
            // var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            loadtrash(body);
        }
    });
    layer.full(index);
};
function note_search(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: path+url,
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);
            body.load(path+"/search.html");
        }
    });
    layer.full(index);
};
$("#searchInput").keydown(function () {
    //回车搜索
    var event = window.event || arguments.callee.caller.arguments[0];
    if (event.keyCode == 13){
        var note_name = $('#searchInput').val().trim();
        if(note_name == ""){
            layer.msg("请输入需要模糊查询笔记",{icon:2,time:1000});
        }else{
            $.ajax({
                url:path+"/note/searchnotes.do",
                data:{"user_id":$.cookie("user_id"),"note_title":note_name},
                type:"post",
                dataType:"json",
                success:function(result){

                },
                error:function(){
                    layer.msg("模糊查询错误")
                }
            });
        }
    }
});
function modifyPWD(){
    layer.prompt({title: '输入当前密码，并确认', formType: 1}, function(oldPass, index){
        layer.close(index);
        $.ajax({
            url:path+"/user/checkpwd.do",
            type:"post",
            data:{"pwd":oldPass,"user_id":$.cookie("user_id")},
            dataType:"json",
            success:function(result){
                if(result.status == 0)
                    layer.prompt({title: '输入新密码，并确认', formType: 1}, function(newPass, index){
                        layer.close(index);
                        layer.prompt({title: '输入新密码，并确认', formType: 1},function(sePass,index){
                            if(newPass != sePass)
                                layer.msg("两次输入密码不一致");
                            else{
                                $.ajax({
                                    url:path+"/user/changepwd.do",
                                    data:{"user_id":$.cookie("user_id"),"password":sePass},
                                    type:"post",
                                    dataType:"json",
                                    success:function(result){
                                        if(result.status == 0){
                                            layer.msg("修改密码成功!",{icon:1,time:1000});
                                        }else{
                                            layer.msg("修改密码错误!",{icon:0,time:1000});
                                        }
                                    },
                                    error:function(){
                                        layer.msg("修改密码错误!",{icon:0,time:1000});
                                    }
                                });
                            }
                            layer.close(index);
                        });
                    });
                else
                    layer.msg("你输入的密码不正确");
            },error:function(){
                layer.msg("修改出现错误",{icon:0,time:1000})
            }
        });

    });
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
						$("#book_dl").append(createDl(bookId,bookName));
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
		window.location.href = path+"/user/tologin.do";
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
						    // console.log(result[array[i]]["data"][j]["note_id"]+"  "+result[array[i]]["data"][j]["note_status_id"]);
							$("dl").eq(i).find("ul#first").append(createUl(result[array[i]]["data"][j]["note_id"],result[array[i]]["data"][j]["note_title"],result[array[i]]["data"][j]["note_status_id"]));
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
function createUl(note_id,note_title,note_status){
	var sul = "";
	// sul += '<ul id="first">';
	sul += '<li><a href="javascript:void(0)" class="note"><i class="Hui-iconfont">&#xe692;</i><span>';
	sul += note_title;
	sul += '</span></a><ul id="second">'
    sul += '<li><a href="javascript:void(0)" class="moveBtn"><i class="Hui-iconfont">&#xe639;</i>移动至</a></li>';
	if (note_status == 1)
	    sul += '<li><a href="javascript:void(0)" class="starBtn"><i class="Hui-iconfont">&#xe69e;</i>收藏</a></li>';
	else if (note_status == 4)
	    sul += '<li><a href="javascript:void(0)" class="remBtn"><i class="Hui-iconfont">&#xe630;</i>取消收藏</a></li>';
	sul += '<li><a href="javascript:void(0)" class="delBtn"><i class="Hui-iconfont">&#xe6e2;</i>回收站</a></li>';
	sul += '</ul></li>';
	// sul += '</ul>';
	var $ul = $(sul);
	//$ul.find("a").attr("data-note_id",note_id);
	$ul.find("a.note").attr("id",note_id);
	$ul.find("a.note").attr("data-title",note_title);
	return $ul;
	// $("dl").eq(index).find("dd").append($ul);
};
//创建笔记本dl元素
function createDl(bookId,bookName){
	var sdl = "";
	sdl += '<dl id="menu-article">';
	sdl += '<dt><i class="Hui-iconfont">&#xe720;</i>';
	sdl += bookName;
	sdl += '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt><dd><ul id="first"></ul></dd></dl>';
	var $dl = $(sdl);
//	将bookid绑定到dl上；
	$dl.attr("data-bookId",bookId);
	return $dl;
	// $("#book_dl").append($dl);
};
//index.html的保存按钮，更新笔记
$("#saveNote").click(function(){
    if($("#articleId").val() == "" || $("#articleId") == null){
        layer.msg('请选择一个笔记!',{icon: 0,time:1000});
    }
    else{
        $.ajax({
            url:path+"/note/updatenote.do",
            data:{"note_id":$("#articleId").val(),
                "note_title":$("#articletitle").val(),
                "note_content":UE.getEditor('editor').getContent()},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status == 0){
                    layer.msg(result.msg,{icon: 1,time:1000});
                    var selectedA = 'a[id='+$("#articleId").val()+']';
                    $(selectedA).attr("data-title",$("#articletitle").val());
                    $(selectedA).children('span').text($("#articletitle").val());
                }
                else{
                    layer.msg(result.msg,{icon: 2,time:1000})
                }
            },
            error:function(){
                layer.msg('笔记更新失败!',{icon: 2,time:1000});
            }
        });
    }
});
//index.html添加笔记
function note_add(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: path+url,
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //console.log(body.html()) //得到iframe页的body内容
            //初始化表单数据的值
            body.find("#user_id").val($.cookie("user_id"));
            for(var i = 0; i < $("#book_dl dl").length ; i++){
                var opt = '<option></option>';
                var $option = $(opt);
                $option.attr("value",$("#book_dl").find("dl").eq(i).attr("data-bookId"));
                $option.text($("#book_dl").find("dl").eq(i).children("dt").text());
                body.find("#note_book_id").append($option); //要修改的每个td的值存为变量传进去
            }
        }
    });
    layer.full(index);
};
/*index.html的添加笔记本*/
function notebook_add(title,url,id){
    var index = layer.open({
        type: 2,
        title: title,
        content: path+url,
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //console.log(body.html()) //得到iframe页的body内容
            //初始化表单数据的值
            body.find("#user_id").val($.cookie("user_id")); //要修改的每个td的值存为变量传进去
        }
    });
    layer.full(index);
}
//和下面一个函数是一起的，三级菜单
$(".Hui-aside").on("click","dl dt",function(){
    //如果点中的是自己，将去除selected类。
    if($(this).hasClass("selected")){
        $(this).removeClass("selected");
    }else{
        $(this).closest('dl').siblings().children('dt').removeClass("selected");
        $(this).addClass("selected");
    }
    //closest 最近的父元素 ，sillings返回所有的同胞元素
    $(this).closest('dl').siblings().find('dd').find('ul#second').slideUp();
    $(this).closest('dl').siblings().find('dd').slideUp();
    $(this).next().slideToggle();
});
$(".Hui-aside").on("click","dl dd a.note",function(){
    $("#articleId").val($(this).attr("id"));
    $("#articletitle").val($(this).attr("data-title"));
    $(this).next().slideToggle();
    $(this).closest('li').siblings().children('ul#second').slideUp();
    $.ajax({
        url:path+"/note/loadNoteContent.do",
        type:"post",
        data:{"note_id":$(this).attr("id")},
        dataType:"json",
        success:function(result){
            UE.getEditor('editor').setContent(result.data);
            //console.log(result.data);
        },
        error:function(){
            layer.msg('笔记加载失败!',{icon: 0,time:1000});
        }
    });
});
//index.html页面的移动笔记按钮
$(".Hui-aside").on("click","dl dd ul#second a.moveBtn",function(){
    var note_book_id_1 = $(this).closest("dl").attr("data-bookid");
    var note_id = $(this).closest("ul").siblings("a").eq(0).attr("id");
    var title = $(this).closest("ul").siblings("a").eq(0).attr("data-title");
    var index = layer.open({
        type: 2,
        title: '移动笔记',
        offset:"auto",
        content:[path+'/note/tomovenote.do', 'no'],
        success: function (layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']];
            //初始化表单数据的值
            body.find("#note_book_id_1").val(note_book_id_1);
            body.find("#note_id").val(note_id);
            body.find("#note_title").val(title);
            // $(this).closest("ul").siblings("a").eq(0).attr("id")
            for(var i = 0; i < $("#book_dl dl").length ; i++){
                var opt = '<option></option>';
                var $option = $(opt);
                $option.attr("value",$("#book_dl").find("dl").eq(i).attr("data-bookId"));
                $option.text($("#book_dl").find("dl").eq(i).children("dt").text());
                body.find("#note_book_id").append($option); //要修改的每个td的值存为变量传进去
            };
        },
    });
    layer.iframeAuto(index);
});
//index.html的收藏笔记功能
$(".Hui-aside").on("click","dl dd ul#second a.starBtn",function(){
    //获取当前的笔记的ID
    var note_id= $(this).closest("ul").siblings("a").eq(0).attr("id");
    var li = $(this).parent();
    $.ajax({
        url:path+"/note/starnote.do",
        data:{"note_id":note_id},
        type:"post",
        dataType:"json",
        success:function(result){
            if (result.status == 0){
                layer.msg('收藏成功',{icon: 1,time:1000});
                li.html('<a href="javascript:void(0)" class="remBtn"><i class="Hui-iconfont">&#xe630;</i>取消收藏</a>');
            }
            else
                layer.msg('收藏失败',{icon:0,time:1000})
        },
        error:function(){
            layer.msg('收藏失败',{icon: 0,time:1000});
        }
    });
});
//index.html页面的取消收藏功能
$(".Hui-aside").on("click","dl dd ul#second a.remBtn",function(){
    // console.log("取消收藏");
    //获取当前的笔记的ID
    var note_id= $(this).closest("ul").siblings("a").eq(0).attr("id");
    var li = $(this).parent();
    $.ajax({
        url:path+"/note/unstarnote.do",
        data:{"note_id":note_id},
        type:"post",
        dataType:"json",
        success:function(result){
            if (result.status == 0){
                layer.msg('取消收藏成功',{icon: 1,time:1000});
                li.html('<a href="javascript:void(0)" class="starBtn"><i class="Hui-iconfont">&#xe69e;</i>收藏</a>');
            }
            else
                layer.msg('取消收藏失败',{icon:0,time:1000})
        },
        error:function(){
            layer.msg('取消收藏失败',{icon: 0,time:1000});
        }
    });
});
//index.html的删除至回收站功能
$(".Hui-aside").on("click","dl dd ul#second a.delBtn",function(){
    var note_id = $(this).closest("ul").siblings("a").eq(0).attr("id");
    layer.confirm('确定删除么？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        //第一个按钮的功能
        $.ajax({
            url:path+"/note/movetotrash.do",
            data:{"note_id":note_id},
            type:"post",
            dataType:"json",
            success:function(result){
                var deleli = 'a[id='+note_id+']';
                $(deleli,parent.document).parent().remove();
                parent.layer.msg('已移至回收站',{icon:1,time:1000});
            },
            error:function(){
                parent.layer.msg("回收错误",{icon:0,time:1000});
            }
        });
    }, function(index){
        //第二个按钮的功能
        layer.close(index);
    });
});
function loadtrash(obj){
    var $body = $(obj);
    $.ajax({
        url:path+"/note/loadtrash.do",
        type:"post",
        data:{"user_id":$.cookie("user_id")},
        dataTyep:"json",
        success:function(result){
            if(result.status == 0){
                var infos = result.data;
                sessionStorage.clear();
                for(var i = 0; i < infos.length;){
                    var str = "";
                    for(var j = 0;j < 5 ;j++){
                        var info = infos[i];
                        var tr = createTr(info);
                        str += tr;
                        i++;
                        if(i >= infos.length)
                            break;
                    }
                    sessionStorage.setItem((Math.ceil(i/5)).toString(),str);
                }
                $body.find("tbody").first().empty();
                $body.find("tbody").first().append(sessionStorage.getItem("1"));
                $body.find("#index").text("1");
                $body.find("strong").first().text(infos.length);
            }else if(result.status == 1){
                parent.layer.msg("回收站为空",{icon:0,time:1000});
            }
            else{
                parent.layer.msg("查看回收站错误",{icon:0,time:1000});
            }
        },
        error:function(){
            parent.layer.msg("查看回收站错误",{icon:0,time:1000});
        }
    })
};
//由于list.html的body动态加载的，所以要用on方法动态绑定，并且是使用document父类
//list页面的搜索按钮
$(document).on('click',"#searchBtn",function(){
    var note_name = $('#searchInput').val().trim();
    if(note_name == ""){
        parent.layer.msg("请输入需要模糊查询笔记",{icon:0,time:1000});
    }else{
        $.ajax({
            url:path+"/note/searchnotes.do",
            data:{"user_id":$.cookie("user_id"),"note_title":note_name},
            type:"post",
            dataType:"json",
            success:function(result){
                var infos = result.data;
                sessionStorage.clear();
                if(result.status == 0){
                    for(var i = 0; i < infos.length;){
                        var str = "";
                        for(var j = 0;j < 5 ;j++){
                            var info = infos[i];
                            var tr = createTr(info);
                            str += tr;
                            i++;
                            if(i >= infos.length)
                                break;
                        }
                        sessionStorage.setItem((Math.ceil(i/5)).toString(),str);
                    }
                    $("tbody").first().empty();
                    $("tbody").first().append(sessionStorage.getItem("1"));
                    $("#index").text("1");
                    $("strong").first().text(infos.length);
                }else{
                    parent.layer.msg(result.msg,{icon:0,time:1000});
                }
            },
            error:function(){
                parent.layer.msg("模糊查询错误",{icon:0,time:1000});
            }
        });
    }
});
//删除笔记 -不经过回收站
function note_remove(obj){
    var a = $(obj);
    parent.layer.confirm('确认要删除么？',
        {btn:["确定"]},
        function(){
            $.ajax({
                url:path+"/note/deletenote.do",
                type:"post",
                data:{"note_id":a.parents("tr").attr("id")},
                dataType:"json",
                success:function(result){
                    if(result.status == 0){
                        window.sessionStorage.getItem($("#index").text());
                        a.parents("tr").remove();
                        window.sessionStorage.setItem($("#index").text(),$("tbody").first().html());
                        parent.layer.msg(result.msg,{icon:1,time:1000});
                        var length = $("strong").text();
                        $("strong").text(length- 1);
                    }
                    else
                        parent.layer.msg(result.msg,{icon:0,time:1000});
                },
                error:function(){
                    parent.layer.msg("删除错误",{icon:0,time:1000});
                }
            });
        });
};
/*加载笔记-内容*/
function note_content(obj){
    var u = $(obj);
    var cont ;
    $.ajax({
        url:path+"/note/loadNoteContent.do",
        data:{"note_id":u.parents("tr").attr("id")},
        type:"post",
        dataType:"json",
        success:function(result){
            if(result.status == 0)
                parent.layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: ['420px', '240px'], //宽高
                    title:"笔记内容",
                    content: result.data
                });
            else
                layer.msg("加载内容失败",{icon:0,time:1000});
        },
        error:function(){
            layer.msg("加载内容失败",{icon:0,time:1000});
        }
    });
};

/*资讯-删除至回收站*/
function note_del(obj){
    var a = $(obj);
    parent.layer.confirm('确认要放入回收站么？',
        {btn:["确定"]},
        function(){
            $.ajax({
                url:path+"/note/movetotrash.do",
                type:"post",
                data:{"note_id":a.parents("tr").attr("id")},
                dataType:"json",
                success:function(result){
                    if(result.status == 0){
                        window.sessionStorage.getItem($("#index").text());
                        a.parents("tr").remove();
                        // a.parent().siblings("td").eq(3).text("回收站");
                        window.sessionStorage.setItem($("#index").text(),$("tbody").first().html())
                        parent.layer.msg("已放入回收站!",{icon:1,time:1000});
                        var length = $("strong").text();
                        $("strong").text(length- 1);
                    }
                    else
                        parent.layer.msg("回收错误！",{icon:0,time:1000});
                },
                error:function(){
                    parent.layer.msg("回收错误！",{icon:0,time:1000});
                }
            });
        });
};
//下一页
function next_page(){
    var currentPage = parseInt($("#index").text());
    if(currentPage == sessionStorage.length)
        parent.layer.msg("已经是最后一页了");
    else{
        $("#box").prop("checked",false);
        var nextPage = (currentPage+1).toString();
        $("tbody").first().empty();
        $("tbody").first().append(sessionStorage.getItem(nextPage));
        $("#index").text(nextPage);
    }
};
//上一页
function last_page(){
    var currentPage = parseInt($("#index").text());
    if(currentPage == 1)
        parent.layer.msg("已经是第一页了");
    else{
        $("#box").prop("checked",false);
        var lastPage = (currentPage-1).toString();
        $("tbody").first().empty();
        $("tbody").first().append(sessionStorage.getItem(lastPage));
        $("#index").text(lastPage);

    }
};
function note_recover(obj){
    var a = $(obj);
    parent.layer.confirm('确认要还原么？',
        {btn:["确定"]},
        function(){
            $.ajax({
                url:path+"/note/trashrecover.do",
                type:"post",
                data:{"note_id":a.parents("tr").attr("id")},
                dataType:"json",
                success:function(result){
                    if(result.status == 0){
                        window.sessionStorage.getItem($("#index").text());
                        a.parents("tr").remove();
                        // a.parent().siblings("td").eq(3).text("回收站");
                        window.sessionStorage.setItem($("#index").text(),$("tbody").first().html());
                        parent.layer.msg("已从回收站还原!",{icon:1,time:1000});
                        var length = $("strong").text();
                        $("strong").text(length- 1);
                    }
                    else
                        parent.layer.msg("还原错误！",{icon:0,time:1000});
                },
                error:function(){
                    parent.layer.msg("还原错误！",{icon:0,time:1000});
                }
            });
        });
}
//list页面的创建tr
function createTr(information) {
    var sta = "";
    var type = "";
    var func = "";
    if (information.status == 1){
        sta = "正常";
        func = `<a style="text-decoration:none" class="ml-5" onClick="note_del(this)" href="javascript:;" title="回收">
					<i class="Hui-iconfont">&#xe6a1;</i>
				</a>`;
    }
    else if(information.status == 2){
        sta = "回收站";
        func = `<a style="text-decoration:none" class="ml-5" onClick="note_recover(this)" href="javascript:;" title="回收">
					<i class="Hui-iconfont">&#xe604;</i>
				</a>`;
    }
    else if(information.status == 4){
        sta = "收藏";
        func = `<a style="text-decoration:none" class="ml-5" onClick="note_del(this)" href="javascript:;" title="回收">
					<i class="Hui-iconfont">&#xe6a1;</i>
				</a>`;
    }
    if(information.type == 1)
        type = "笔记";
    else if(information.type == 0)
        type = "笔记本"
    var str = `
		<tr class="text-c" id="${information.id}">
			<td><input type="checkbox" value="" name=""></td>
			<td id="name">
				<u style="cursor:pointer" class="text-primary" onClick="note_content(this)" title="查看">
				${information.name}
				</u>
			</td>
			<td id="location">${information.location}</td>
			<td id="status">${sta}</td>
			<td id="type">${type}</td>
			<td id="updated_at">${getSmpFormatDateByLong(information.updated_at,true)}</td>
			<td id="created_at">${getSmpFormatDateByLong(information.created_at,true)}</td>
			<td class="f-14 td-manage">
				${func}
				<a style="text-decoration:none" class="ml-5" onClick="note_remove(this)" href="javascript:;" title="删除">
					<i class="Hui-iconfont">&#xe6e2;</i>
				</a>
			</td>
		</tr>
	`;
    return str;
};
function chooseAll(obj){
    var $box = $(obj);
    if($box.prop("checked") == true)
        for(var i = 0; i < $("tbody").children("tr").length; i++)
            $("tbody").children("tr").eq(i).find("td").eq(0).children("input").prop("checked",true);
    else
        for(var i = 0; i < $("tbody").children("tr").length; i++)
            $("tbody").children("tr").eq(i).find("td").eq(0).children("input").prop("checked",false);
};
function notestotrash(){
    var array = new Array();
    for(var i = 0; i < $("tbody").children().length; i++){
        if($("tbody").children("tr").eq(i).children("td").eq(0).children("input").prop("checked") == true){
            array[i] = $("tbody").children("tr").eq(i).attr("id");
        }
    }
    if(array.length == 0)
        parent.layer.msg("选中的记录数目为0！",{icon:0,time:1000});
    else{
        var index = parent.layer.confirm('确定回收这些记录么？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            checkBox2(array);
        }, function(index){
            layer.close(index);
        });
    }
};
function checkBox2(array){
    $.ajax({
        url:path+"/note/movenotestotrash.do",
        type:"post",
        data:{"ids":array.join(",")},
        dataType:"json",
        success:function(result){
            if(result.status == 0){
                parent.layer.msg(result.msg,{icom:0,time:1000});
                for(var i = 0 ; i < array.length ; i ++){
                    var str = "tr[id="+array[i]+"]";
                    $(str).remove();
                    window.sessionStorage.setItem($("#index").text(),$("tbody").first().html());
                }
                var length = $("strong").text();
                $("strong").text(length- array.length);
            }else{
                parent.layer.msg(result.msg,{icon:0,time:1000});
            }
        },
        error:function(){
            parent.layer.msg("批量回收错误",{icon:0,time:1000});
        }
    });
}
function datadel(){
    var array = new Array();
    for(var i = 0; i < $("tbody").children().length; i++){
        if($("tbody").children("tr").eq(i).children("td").eq(0).children("input").prop("checked") == true){
            array[i] = $("tbody").children("tr").eq(i).attr("id");
        }
    }
    if(array.length == 0)
        parent.layer.msg("选中的记录数目为0！",{icon:0,time:1000});
    else{
        var index = parent.layer.confirm('确定删除这些记录么？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            checkBox(array);
        }, function(index){
            layer.close(index);
        });
    }
};
function checkBox(array){
        $.ajax({
            url:path+"/note/deletenotes.do",
            type:"post",
            data:{"ids":array.join(",")},
            dataType:"json",
            success:function(result){
                if(result.status == 0){
                    parent.layer.msg(result.msg,{icom:0,time:1000});
                    for(var i = 0 ; i < array.length ; i ++){
                        var str = "tr[id="+array[i]+"]";
                        $(str).remove();
                        window.sessionStorage.setItem($("#index").text(),$("tbody").first().html());
                    }
                    var length = $("strong").text();
                    $("strong").text(length- array.length);
                }else{
                    parent.layer.msg(result.msg,{icon:0,time:1000});
                }
            },
            error:function(){
                parent.layer.msg("批量删除错误",{icon:0,time:1000});
            }
        });
};
function trashnotesrecovery(){
    var array = new Array();
    for(var i = 0; i < $("tbody").children().length; i++){
        if($("tbody").children("tr").eq(i).children("td").eq(0).children("input").prop("checked") == true){
            array[i] = $("tbody").children("tr").eq(i).attr("id");
        }
    }
    if(array.length == 0)
        parent.layer.msg("选中的记录数目为0！",{icon:0,time:1000});
    else{
        var index = parent.layer.confirm('确定恢复这些记录么？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            checkBox3(array);
        }, function(index){
            layer.close(index);
        });
    }
}
function checkBox3(array){
    $.ajax({
        url:path+"/note/trashNotesRecover.do",
        type:"post",
        data:{"ids":array.join(",")},
        dataType:"json",
        success:function(result){
            if(result.status == 0){
                parent.layer.msg(result.msg,{icom:0,time:1000});
                for(var i = 0 ; i < array.length ; i ++){
                    var str = "tr[id="+array[i]+"]";
                    $(str).remove();
                    window.sessionStorage.setItem($("#index").text(),$("tbody").first().html());
                }
                var length = $("strong").text();
                $("strong").text(length- array.length);
            }else{
                parent.layer.msg(result.msg,{icon:0,time:1000});
            }
        },
        error:function(){
            parent.layer.msg("批量回收错误",{icon:0,time:1000});
        }
    });
}
//扩展Date的format方法
Date.prototype.format = function (format) {
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		"S": this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 *转换日期对象为日期字符串
 * @param date 日期对象
 * @param isFull 是否为完整的日期数据,
 *               为true时, 格式如"2000-03-05 01:05:04"
 *               为false时, 格式如 "2000-03-05"
 * @return 符合要求的日期字符串
 */
function getSmpFormatDate(date, isFull) {
	var pattern = "";
	if (isFull == true || isFull == undefined) {
		pattern = "yyyy-MM-dd hh:mm:ss";
	} else {
		pattern = "yyyy-MM-dd";
	}
	return getFormatDate(date, pattern);
}
/**
 *转换当前日期对象为日期字符串
 * @param date 日期对象
 * @param isFull 是否为完整的日期数据,
 *               为true时, 格式如"2000-03-05 01:05:04"
 *               为false时, 格式如 "2000-03-05"
 * @return 符合要求的日期字符串
 */

function getSmpFormatNowDate(isFull) {
	return getSmpFormatDate(new Date(), isFull);
}
/**
 *转换long值为日期字符串
 * @param l long值
 * @param isFull 是否为完整的日期数据,
 *               为true时, 格式如"2000-03-05 01:05:04"
 *               为false时, 格式如 "2000-03-05"
 * @return 符合要求的日期字符串
 */

function getSmpFormatDateByLong(l, isFull) {
	return getSmpFormatDate(new Date(l), isFull);
}
/**
 *转换long值为日期字符串
 * @param l long值
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */

function getFormatDateByLong(l, pattern) {
	return getFormatDate(new Date(l), pattern);
}
/**
 *转换日期对象为日期字符串
 * @param l long值
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */
function getFormatDate(date, pattern) {
	if (date == undefined) {
		date = new Date();
	}
	if (pattern == undefined) {
		pattern = "yyyy-MM-dd hh:mm:ss";
	}
	return date.format(pattern);
}