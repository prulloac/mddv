var app = angular.module('mddv',['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/login',{
            templateUrl: 'js/views/login.html',
            controller: 'LoginControler'
        })
        .otherwise({
            redirectTo: '/index'
        });
});