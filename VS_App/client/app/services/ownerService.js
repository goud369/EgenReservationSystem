/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .service('ownerService', ownerService);

    ownerService.$inject = ['$q', '$http'];
    function ownerService($q, $http) {
          
        var ownerService = this;

        ownerService.getOwnerDetails = function (id) {
             var defer = $q.defer();
            $http.get('http://localhost:8080/vs/owners/'+id)
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;

        };
    }
})();