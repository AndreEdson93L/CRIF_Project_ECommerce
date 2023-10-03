// environment.ts
const mAuthPath = "http://localhost:8090/api/v1/authentication"
const mAccountPath = "http://localhost:8081/api/v1/user-management"
const mAccountAdminSectionPath = "http://localhost:8081/api/v1/admin-management"


//List of all endpoints
export const environment = {
    //production: false,
    registerUrl: mAccountPath+'/register-account',
    loginUrl: mAuthPath+'/login',
    getUserDetail: mAccountPath+'/get-user-details',
    deleteUserDetail: mAccountPath+'/delete-user-details',
    updateUserDetail: mAccountPath+'/update-user-details',
    getAllUsersDetails: mAccountAdminSectionPath+'/get-all-users-details',
    deleteUserAdmin: mAccountAdminSectionPath+'/delete-user',
    promoteUserAdmin: mAccountAdminSectionPath+'/promote-user',
    updateUserAdmin: mAccountAdminSectionPath+'/update-user-details',
    getUserAdmin: mAccountAdminSectionPath+'/get-user-details',
  };
  

//List endpoints with jwt in header of HttpRequest
  export const includedUrls: string[] = [
    environment.getUserDetail,
    environment.deleteUserDetail,
    environment.updateUserDetail,
    environment.getAllUsersDetails,
    environment.deleteUserAdmin,
    environment.promoteUserAdmin,
    environment.updateUserAdmin,
    environment.getUserAdmin,
    // add others endpoints
  ];