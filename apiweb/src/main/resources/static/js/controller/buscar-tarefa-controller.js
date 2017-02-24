appLab.controller("buscarTarefaController", function ($scope, $http, $routeParams) {

    $scope.tarefas = [];
    $scope.tarefa = {};
    $scope.subTarefa = {};
    $scope.subTarefas = [];
    $scope.subTarefaEditada = {};
    $scope.tipoBusca  = {};
    $scope.valorFiltro;
    $scope.atributoParaFiltro;



    $scope.filtro = function () {

        $scope.tipoBusca = {};

        if($scope.atributoParaFiltro == "nome") {
            $scope.tipoBusca.nome = $scope.valorFiltro;
        } else if($scope.atributoParaFiltro == "descricao") {
            $scope.tipoBusca.descricao = $scope.valorFiltro;
        } else if($scope.atributoParaFiltro == "data") {
            $scope.tipoBusca.data = $scope.valorFiltro;
        } else if($scope.atributoParaFiltro == "categoria") {
            $scope.tipoBusca.categoria = $scope.valorFiltro;
        } else if($scope.atributoParaFiltro == "nomeLista") {
            $scope.tipoBusca.nomeLista = $scope.valorFiltro;
        } else if($scope.atributoParaFiltro == "prioridade") {
            $scope.tipoBusca.prioridade = $scope.valorFiltro;
        } else {
            $scope.tipoBusca.complete = $scope.valorFiltro;
        }


    };


    $scope.buscarTarefa = function () {
        //primeiro get
        $http({
            method:'GET',
            url:'http://181.41.212.237:80/listarTarefas'


        }).then(function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            $scope.tarefas = response.data;

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };

    $scope.vaiParaTela = function (subTarefa) {

        $scope.subTarefa.nome = subTarefa.nome;

        console.log($scope.subTarefa.nome);

    };


    $scope.buscarSubTarefa = function () {
        //primeiro get
        $http({
            method:'GET',
            url:'http://181.41.212.237:80/buscarSubTarefa/'+$routeParams.nomeSubTarefa


        }).then(function (response) {

            // $scope.sobrenome = response.status;
            $scope.subTarefa = response.data;

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.buscarTodasSubTarefas = function () {
        //primeiro get
        $http({
            method:'GET',
            url:'/buscaTodasSubTarefas'


        }).then(function (response) {

            // $scope.sobrenome = response.status;
            $scope.subTarefas = response.data;

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.buscarTodasSubTarefas();

    $scope.buscarSubTarefa();

    $scope.editarSubTarefa = function (subTarefa) {
        $scope.subTarefa = subTarefa;
        $scope.subTarefaEditada.nome = subTarefa.nome;
        $scope.subTarefaEditada.nomeTarefa = subTarefa.nomeTarefa;
    };



    $scope.editarNomeSubTarefa = function () {

        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarNomeSubTarefa/'+$scope.subTarefaEditada.nome+'/'+$scope.subTarefaEditada.nomeTarefa+'/'+$scope.subTarefa.nome



        }).then(function (response) {

            $scope.subTarefas.push(response.data);
            $scope.subTarefaEditada.nome = $scope.subTarefa.nome;

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.trocarPrioridadeSubTarefa = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/trocarPrioridadeSubTarefa/'+$scope.subTarefaEditada.nome+'/'+$scope.subTarefaEditada.nomeTarefa+'/'+$scope.subTarefa.prioridade


        }).then(function (response) {

            $scope.subTarefas.push(response.data);
            $scope.subTarefaEditada.prioridade = $scope.subTarefa.prioridade;

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.marcarTarefa = function (nomeTarefa, nomeLista) {
        //primeiro post
        $http({
            method:'POST',
            url:'/marcarTarefa/'+nomeTarefa+'/'+nomeLista

        }).then(function (response) {

            $scope.buscarTarefa();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };



    $scope.editarDescricaoSubTarefa = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarDescricaoSubTarefa/'+$scope.subTarefaEditada.nome+'/'+$scope.subTarefaEditada.nomeTarefa+'/'+$scope.subTarefa.descricao

        }).then(function (response) {

            $scope.subTarefas.push(response.data);
            $scope.subTarefaEditada.descricao = $scope.subTarefa.descricao;

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };


    $scope.editarDataSubTarefa = function () {

        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarDataSubTarefa/'+$scope.subTarefaEditada.nome+'/'+$scope.subTarefaEditada.nomeTarefa+'/'+$scope.subTarefa.data

        }).then(function (response) {

            $scope.subTarefas.push(response.data);

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.editarCategoriaSubTarefa = function () {

        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarCategoriaSubTarefa/'+$scope.subTarefaEditada.nome+'/'+$scope.subTarefaEditada.nomeTarefa+'/'+$scope.subTarefa.categoria

        }).then(function (response) {

            $scope.subTarefas.push(response.data);

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };


    $scope.removerSubTarefa = function () {

        $http({
            method:'POST',
            url:'http://181.41.212.237:80/removeSubTarefa/'+$scope.subTarefa.nome+'/'+$scope.subTarefa.nomeTarefa

        }).then(function (response) {

            $scope.buscarTodasSubTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });

        window.location.replace("/");

    };


    $scope.excluirTarefa = function (nomeTarefa, nomeLista) {

        $http({
            method:'POST',
            url:'http://181.41.212.237:80/removerTarefa/'+nomeTarefa+'/'+nomeLista


        }).then(function (response) {

            $scope.listas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            $scope.buscarTarefa();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
        window.location.replace("/");
    };

});