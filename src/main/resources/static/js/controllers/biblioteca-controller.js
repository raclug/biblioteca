angular.module('biblioteca').controller('BibliotecaController', function($scope, $window, $http) {

    $scope.autor = {};
    $scope.mensagem = '';
    $scope.nacionalidades = {};
    $scope.foto;
    $scope.foto64;

    $scope.cadastroAutor = function() {
    	$window.location.href= 'biblioteca/cadastroAutor';
    }
    
    $scope.incluir = function () {
    	
    	
    	var file = $scope.foto;
    	var fd = new FormData();
    	fd.append('file', file);
    	fd.append('autor',angular.toJson($scope.autor, true));
    	$http.post('/biblioteca/incluirAutor', fd, {
    		transformRequest : angular.identity,
    		headers : {
    			'Content-Type' : undefined
    		}
    		}).then(function() {
    			$scope.mensagem = 'Autor inserido com sucesso!';
            	$scope.autor = {};
            	$scope.foto = null;
    		}).catch(function(erro) {
    			console.log(erro);
    	})
        
      };
      
      $scope.carregarNacionalidades = function() {
          $http.get('/biblioteca/nacionalidades').then(function (nacionalidades) {
        	$scope.nacionalidades = angular.fromJson(nacionalidades).data;
        	console.log($scope.nacionalidades);
          });
        };
        
        $scope.listar = function() {
        	$http.get('/biblioteca/listarAutor').then(function (autor) {
            	$scope.autor = angular.fromJson(autor).data;
            	$scope.foto64 = base64ArrayBuffer($scope.autor.foto);
            	
            	
              });
        };
        
        $scope.mudarFoto = function() {
        	$scope.foto64 = base64ArrayBuffer($scope.foto);
        };
        
       $scope.carregarNacionalidades();
       $scope.listar();
});