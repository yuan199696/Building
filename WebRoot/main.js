function getLang(){
	
	//此处配置关于国际化
	return "en_US";
}

history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
})