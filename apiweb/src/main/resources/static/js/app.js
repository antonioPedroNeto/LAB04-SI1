//Criacao do modulo principal da aplicacao
var appLab = angular.module("appLab", ['ngRoute']);


appLab.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/tarefas', {

            templateUrl: 'view/criarTarefa.html',
            controller: 'tarefaController'
        })
        .when('/listas', {

            templateUrl: 'view/criarLista.html',
            controller: 'tarefaController'

        })
        .when('/editar', {

            templateUrl: 'view/editar.html',
            controller: 'tarefaController'

        }).when('/buscarTarefa', {

            templateUrl: 'view/buscarTarefa.html',
            controller: 'buscarTarefaController'

        }).when('/buscarSubTarefa/:nomeSubTarefa', {

            templateUrl: 'view/buscarSubTarefa.html',
            controller: 'buscarTarefaController'

         }).when('/editarSubTarefa', {

            templateUrl: 'view/editarSubTarefa.html',
            controller: 'buscarTarefaController'

        }).when('/', {

            templateUrl: 'view/inicio.html',
            controller: 'buscarTarefaController'

        }).when('/subtarefas', {

            templateUrl: 'view/criarSubTarefa.html',
            controller: 'tarefaController'

        }).otherwise({
            redirectTo: '/tarefas'
        });

    $locationProvider.html5Mode(true);


});