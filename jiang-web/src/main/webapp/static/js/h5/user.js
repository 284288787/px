$(function(){
	Lottery.config({
		basePath: basePath
	});
	var login = Lottery.isLogin();
	if(login){
		var mem = Lottery.getMember();
		$(".head-img span").text(mem.name ? mem.name : '未设置');
		$(".goldNums").text(mem.goldNums ? mem.goldNums : '0');
		$(".ballNums").text(mem.ballNums ? mem.ballNums : '0');
		$(".head-img img").attr("src", mem.headPic ? basePath+mem.headPic : basePath+'static/images/head.png');
		$(".yqm span").text("P"+mem.memberId);
	}
});