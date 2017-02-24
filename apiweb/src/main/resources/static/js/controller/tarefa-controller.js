appLab.controller("tarefaController", function ($scope, $http) {

    $scope.nome = '232';
    // $scope.sobrenome = "Diogenes";
    $scope.tarefas = [];
    $scope.listas = [];
    $scope.subTarefas = [];

    $scope.lista = {};

    $scope.tarefa = {};

    $scope.subTarefa = {};

    $scope.tarefaEditada = {};

    $scope.tarefasNaoFeitas = [];

    $scope.situacao = [];

    $scope.carregarTarefas = function () {
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

    $scope.carregarListas = function () {
        //primeiro get
        $http({
            method:'GET',
            url:'http://181.41.212.237:80/listas'


        }).then(function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            $scope.listas = response.data;

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };

    $scope.carregarListas();
    $scope.carregarTarefas();

    $scope.criaLista = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/criaLista',
            data:$scope.lista


        }).then(function (response) {

            $scope.listas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

        }, function (response) {

            $scope.nome = response.data;
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
            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
        window.location.replace("/");
    };

    $scope.criaTarefa = function () {

        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/criaTarefa',
            data:$scope.tarefa


        }).then(function (response) {

            $scope.tarefas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();
            $scope.carregarListas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });

        window.location.replace("/");
    };

    $scope.criaSubTarefa = function () {

        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/criaSubTarefa',
            data:$scope.subTarefa


        }).then(function (response) {

            $scope.subTarefas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });

        window.location.replace("/");
    };


    $scope.editarTarefa = function (tarefa) {

        $scope.tarefa = tarefa;
        $scope.tarefaEditada.nome = tarefa.nome;
        $scope.tarefaEditada.nomeLista = tarefa.nomeLista;

    };

    $scope.editarNomeTarefa = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarNomeTarefa/'+$scope.tarefaEditada.nome+'/'+$scope.tarefaEditada.nomeLista+'/'+$scope.tarefa.nome



        }).then(function (response) {

            $scope.tarefas.push(response.data);
            $scope.tarefaEditada.nome = $scope.tarefa.nome;
            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };

    $scope.editarPrioridadeTarefa = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/trocarPrioridadeTarefa/'+$scope.tarefaEditada.nome+'/'+$scope.tarefaEditada.nomeLista+'/'+$scope.tarefa.prioridade




        }).then(function (response) {

            $scope.tarefas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };


    $scope.editarDescricaoTarefa = function () {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarDescricaoTarefa/'+$scope.tarefaEditada.nome+'/'+$scope.tarefaEditada.nomeLista+'/'+$scope.tarefa.descricao

        }).then(function (response) {

            $scope.tarefas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };



    $scope.marcarTarefa = function (tarefa) {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/marcarTarefa/'+tarefa.nome+'/'+tarefa.nomeLista

        }).then(function (response) {

            $scope.tarefas.push(response.data);

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });


    };


    $scope.carregarTarefasNaoFeitas = function () {
        //primeiro get
        $http({
            method:'GET',
            url:'http://181.41.212.237:80/tarefasNaoFeitas'


        }).then(function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            $scope.tarefasNaoFeitas = response.data;

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });
    };

    $scope.carregarTarefasNaoFeitas();


    $scope.excluirUmaLista = function (lista) {
        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/removerTodasLista/'+lista.nome


        }).then(function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            $scope.listas = {};

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });

        window.location.replace("/");

    };


    $scope.removerListas = function () {

        //primeiro post
        $http({
            method:'POST',
            url:'http://181.41.212.237:80/removerListas'


        }).then(function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;
            // $scope.listas = response.data;

            $scope.carregarTarefas();

        }, function (response) {

            $scope.nome = response.data;
            // $scope.sobrenome = response.status;

        });

        window.location.replace("/");

    }

    $scope.editarDataTarefa = function () {

        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarDataTarefa/'+$scope.tarefaEditada.nome+'/'+$scope.tarefaEditada.nomeTarefa+'/'+$scope.tarefa.data

        }).then(function (response) {


            $scope.carregarTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });
    };

    $scope.editarCategoriaTarefa = function () {


        $http({
            method:'POST',
            url:'http://181.41.212.237:80/editarCategoriaTarefa/'+$scope.tarefaEditada.nome+'/'+$scope.tarefaEditada.nomeTarefa+'/'+$scope.tarefa.categoria

        }).then(function (response) {


            $scope.carregarTarefas();

        }, function (response) {

            // $scope.sobrenome = response.status;

        });

    };

});

