/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('profileController', profileController)
    // .controller('ReservationDetailController', ReservationDetailController);

    profileController.$inject = ['ownerService', '$rootScope', '$scope','profileService'];


    function profileController(ownerService, $rootScope, $scope,profileService) {
        var profileVm = this;
        var id=$rootScope.id;
        //var reserVm;
      /*  profileVm.FileUploadService = function () {
            $scope.Message = "";
            $scope.FileInvalidMessage = "";
            $scope.SelectedFileForUpload = null;
            $scope.FileDescription = "";
            $scope.IsFormSubmitted = false;
            $scope.IsFileValid = false;
            $scope.IsFormValid = false;

            //Form Validation
            $scope.$watch("f1.$valid", function (isValid) {
                $scope.IsFormValid = isValid;
            });


            // THIS IS REQUIRED AS File Control is not supported 2 way binding features of Angular
            // ------------------------------------------------------------------------------------
            //File Validation
            $scope.ChechFileValid = function (file) {
                var isValid = false;
                if ($scope.SelectedFileForUpload != null) {
                    if ((file.type == 'image/png' || file.type == 'image/jpeg' || file.type == 'image/gif' || file.type == 'image/JPG') && file.size <= (512 * 1024)) {
                        $scope.FileInvalidMessage = "";
                        isValid = true;
                    }
                    else {
                        $scope.FileInvalidMessage = "Selected file is Invalid. (only file type png, jpeg and gif and 512 kb size allowed)";
                    }
                }
                else {
                    $scope.FileInvalidMessage = "Image required!";
                }
                $scope.IsFileValid = isValid;
            };

            //File Select event 
            $scope.selectFileforUpload = function (file) {
                $scope.SelectedFileForUpload = file[0];
            }
            //----------------------------------------------------------------------------------------

            //Save File
            $scope.SaveFile = function () {
                $scope.IsFormSubmitted = true;
                $scope.Message = "";
                $scope.ChechFileValid($scope.SelectedFileForUpload);
                if ($scope.IsFormValid && $scope.IsFileValid) {
                    profileService.UploadFile($scope.SelectedFileForUpload,id).then(function (d) {
                        alert(d.Message);
                        ClearForm();
                    }, function (e) {
                        alert(e);
                    });
                }
                else {
                    $scope.Message = "All the fields are required.";
                }
            };
            //Clear form 
            function ClearForm() {
                $scope.FileDescription = "";
                //as 2 way binding not support for File input Type so we have to clear in this way
                //you can select based on your requirement
                angular.forEach(angular.element("input[type='file']"), function (inputElem) {
                    angular.element(inputElem).val(null);
                });

                $scope.f1.$setPristine();
                $scope.IsFormSubmitted = false;
            }
        }*/
        $scope.futureWork=function(){
            alert("Sorry for the inconvience this has been not done due to health issues !!!");
        };
        init();
        function init() {
            ownerService.getOwnerDetails($rootScope.id)
                    .then(function (OwnerDetails) {
                        profileVm.OwnerDetails = OwnerDetails;
                    }, function (error) {
                        console.log(error);
                    });

        }

    }

})();