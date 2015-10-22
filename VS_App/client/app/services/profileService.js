/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .service('profileService', profileService);

    profileService.$inject = ['$q', '$http'];
    function profileService($q, $http) {
          var profileService=this;
   profileService.UploadFile = function(file, id) {
       
        var formData = new FormData();
        formData.append("file", file);
        var defer = $q.defer();
        $http.put("http://localhost:8080/vs/owners/"+id+"/"+file)
        .success(function (d) {
            defer.resolve(d);
        })
        .error(function () {
            defer.reject("File Upload Failed!");
        });
 
        return defer.promise;

    };
    return profileService;
    }
})();

