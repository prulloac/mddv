var app = angular.module('mddv',['ngRoute','ngMaterial','ngMessages']);

app.config(function($routeProvider){
    $routeProvider
        .when('/login',{
            templateUrl: 'js/views/login.html',
            controller: 'LoginController'
        })
        .otherwise({
            redirectTo: '/index'
        });
});