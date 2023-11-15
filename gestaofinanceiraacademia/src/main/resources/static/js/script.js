var btnMenu = document.querySelector(".btn-menu");
var menu = document.querySelector(".menu")

btnMenu.addEventListener('click', function(){
    menu.classList.toggle('menu-open')
    btnMenu.classList.toggle('x')
})

menu.addEventListener('click', function(){
    menu.classList.remove('menu-open')
    btnMenu.classList.toggle('x')
})