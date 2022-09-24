package com.axrorabdurayimjonov.adidas.api

import com.axrorabdurayimjonov.adidas.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("token")
    fun checkUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginModel>

    @DELETE("remove_logo/{logo_id}")
    fun deleteBranch(
        @Path("logo_id") int: Int,
        @Body delete:BranchDeleteImageModel
    ):Call<List<BranchDeleteImageModel>>

    @PUT("update_currency/{currency_id}")
    fun putCurrently(
        @Path("currency_id")id: Int,
        @Body putCurrently:CurrentlyUpdateModel
    ):Call<CurrentlyUpdateModel>

    @PUT("update_category/{category_id}")
    fun updateCategory(
        @Path("category_id") id: Int,
        @Body updateCategory: UpdateCategoryModel
    ):Call<UpdateCategoryModel>

    @PUT("update_market/{market_id}")
    fun putProvider(
        @Path("market_id") id: Int,
        @Body putProvider: UpdateProviderModel
    ): Call<UpdateProviderModel>

    @POST("create_logo/{branch_id}")
    fun logoBranchPost(
        @Path("branch_id") int: Int,
        @Body postLogoBranch:BranchPostImageModel
    ):Call<List<BranchPostImageModel>>

    @POST("create_category")
    fun categoryPost(
        @Body postCategory: CategoryPostModel
    ): Call<CategoryPostModel>

    @POST("create_currency")
    fun currencyPost(
        @Body postCurrenteModel: CurrentePostModel
    ): Call<CurrentePostModel>

    @POST("create_market")
    fun postProvider(
        @Body postProviderModel: ProviderPostAddModel
    ): Call<ProviderPostAddModel>

    @POST("create_user")
    fun postEmployee(
        @Body postEmployeePostModel: EmployeePostModel
    ): Call<EmployeePostModel>

    @POST("create_branch")
    fun postBranch(
        @Body postBranchModel: PostBranchModel
    ): Call<PostBranchModel>

    @GET("get_currencies")
    fun currentlyGet(): Call<CurrentlyGetModel>

    @GET("get_warehouse_products/{warehouse_id}")
    fun warehouseProducts(
        @Path("warehouse_id") warehouse_id:Int,
        @Query("category_id") category_id: Int,
        @Query("page") page: Int,
        @Query("group")group:Boolean,
        @Query("limit") limit: Int
    ):Call<List<Warehouse_Products_Get_Model>>

    @GET("get_categories")
    fun categoryGet(
        @Query("category_id") category_id: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<CategoryGetSettingModel>

    @GET("get_branches")
    fun branchGet(): Call<BranchModel>

    @GET("get_warehouses")
    fun warehouseget(): Call<List<WarehouseModel>>

    @GET("get_users")
    fun employeesgetWorkers(
        @Query("branch_id") branchId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<EmployeesWorkerModel>

    @GET("get_markets")
    fun providerGet(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<ProviderModel>

}