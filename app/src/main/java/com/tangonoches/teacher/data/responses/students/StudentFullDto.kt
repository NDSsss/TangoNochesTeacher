package com.tangonoches.teacher.data.responses.students

import com.google.gson.JsonObject
import com.tangonoches.teacher.data.models.StudentFullModel
import com.tangonoches.teacher.domain.extensions.putIdIfNotDefault
import com.tangonoches.teacher.domain.extensions.putIfNotDefault
import com.tangonoches.teacher.domain.extensions.putNullIfEmpty
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import org.json.JSONObject

data class StudentFullDto(
    val barcode_id: Long = DEFAULT_ID,
    val created_at: String? = "",
    val extra_info: String? = "",
    val facebook_profile_id: Long = DEFAULT_ID,
    val facebook_profile_link: String? = "",
    val id: Long = DEFAULT_ID,
    val instagram_profile_id: Long = DEFAULT_ID,
    val instagram_profile_link: String? = "",
    val name: String? = "",
    val phone: String? = "",
    val photo_link: String? = "",
    val push_token: String? = "",
    val updated_at: String? = "",
    val vk_profile_id: Long = DEFAULT_ID,
    val vk_profile_link: String? = ""
)

fun StudentFullDto.toModel(): StudentFullModel =
    StudentFullModel(
        barcodeId = barcode_id,
        name = name ?: "",
        id = id,
        phone = phone ?: "",
        createdAt = created_at ?: "",
        extraInfo = extra_info ?: "",
        facebookProfileId = facebook_profile_id,
        facebookProfileLink = facebook_profile_link ?: "",
        instagramProfileId = instagram_profile_id,
        instagramProfileLink = instagram_profile_link ?: "",
        photoLink = photo_link ?: "",
        pushToken = push_token ?: "",
        updatedAt = updated_at ?: "",
        vkProfileId = vk_profile_id,
        vkProfilelink = vk_profile_link ?: ""
    )

data class StudentFullDtoToUpdate(
    val id: Long,
    val barcode_id: Long = DEFAULT_ID,
    val extra_info: String? = "",
    val facebook_profile_id: Long = DEFAULT_ID,
    val facebook_profile_link: String? = "",
    val instagram_profile_id: Long = DEFAULT_ID,
    val instagram_profile_link: String? = "",
    val name: String,
    val phone: String? = "",
    val photo_link: String? = "",
    val push_token: String? = "",
    val vk_profile_link: String? = ""
)

data class StudentFullDtoToSave(
    val barcode_id: Long = DEFAULT_ID,
    val extra_info: String? = "",
    val facebook_profile_id: Long = DEFAULT_ID,
    val facebook_profile_link: String? = "",
    val instagram_profile_id: Long = DEFAULT_ID,
    val instagram_profile_link: String? = "",
    val name: String,
    val phone: String? = "",
    val photo_link: String? = "",
    val push_token: String? = "",
    val vk_profile_link: String? = ""
)

fun StudentFullModel.toUpdateDto(): StudentFullDtoToUpdate =
    StudentFullDtoToUpdate(
        id = id,
        barcode_id = barcodeId,
        extra_info = extraInfo,
        facebook_profile_id = facebookProfileId,
        facebook_profile_link = facebookProfileLink,
        instagram_profile_id = instagramProfileId,
        instagram_profile_link = instagramProfileLink,
        name = name,
        phone = phone,
        photo_link = photoLink,
        push_token = pushToken,
        vk_profile_link = vkProfilelink
    )

fun StudentFullModel.toUpdateMap(): Map<String, Any?> {
    val resultMap: HashMap<String, Any?> = hashMapOf()

    resultMap.putIfNotDefault("id", id)
    resultMap.putIfNotDefault("barcode_id", barcodeId)
    resultMap.putIfNotDefault("facebook_profile_id", facebookProfileId)
    resultMap.putIfNotDefault("instagram_profile_id", instagramProfileId)
    resultMap["extra_info"] = extraInfo
    resultMap["facebook_profile_link"] = facebookProfileLink
    resultMap["instagram_profile_link"] = instagramProfileLink
    resultMap["name"] = name
    resultMap["phone"] = phone
    resultMap["photo_link"] = photoLink
    resultMap["push_token"] = pushToken
    resultMap["vk_profile_link"] = vkProfilelink

    return resultMap
}

fun StudentFullModel.toUpdateJsonObject(): JSONObject {
    val resultObject = JSONObject()

    resultObject.putIdIfNotDefault("id", id)
    resultObject.putIdIfNotDefault("barcode_id", barcodeId)
    resultObject.putIdIfNotDefault("facebook_profile_id", facebookProfileId)
    resultObject.putIdIfNotDefault("instagram_profile_id", instagramProfileId)
    resultObject.put("extra_info",extraInfo)
    resultObject.put("facebook_profile_link",facebookProfileLink)
    resultObject.put("instagram_profile_link",instagramProfileLink)
    resultObject.put("name",name)
    resultObject.putNullIfEmpty("phone",phone)
    resultObject.put("photo_link",photoLink)
    resultObject.put("push_token",pushToken)
    resultObject.put("vk_profile_link",vkProfilelink)

    return resultObject
}

fun StudentFullModel.toSaveDto(): StudentFullDtoToSave =
    StudentFullDtoToSave(
        barcode_id = barcodeId,
        extra_info = extraInfo,
        facebook_profile_id = facebookProfileId,
        facebook_profile_link = facebookProfileLink,
        instagram_profile_id = instagramProfileId,
        instagram_profile_link = instagramProfileLink,
        name = name,
        phone = phone,
        photo_link = photoLink,
        push_token = pushToken,
        vk_profile_link = vkProfilelink
    )

fun StudentFullModel.toSaveMap(): Map<String, Any?> {
    val resultMap: HashMap<String, Any?> = hashMapOf()

    resultMap.putIfNotDefault("barcode_id", barcodeId)
    resultMap.putIfNotDefault("facebook_profile_id", facebookProfileId)
    resultMap.putIfNotDefault("instagram_profile_id", instagramProfileId)
    resultMap["extra_info"] = extraInfo
    resultMap["facebook_profile_link"] = facebookProfileLink
    resultMap["instagram_profile_link"] = instagramProfileLink
    resultMap["name"] = name
    resultMap.putNullIfEmpty("phone", phone)
    resultMap["photo_link"] = photoLink
    resultMap["push_token"] = pushToken
    resultMap["vk_profile_link"] = vkProfilelink

    return resultMap
}

fun StudentFullModel.toSaveJsonObject(): JSONObject {
    val resultObject = JSONObject()

    resultObject.putIdIfNotDefault("barcode_id", barcodeId)
    resultObject.putIdIfNotDefault("facebook_profile_id", facebookProfileId)
    resultObject.putIdIfNotDefault("instagram_profile_id", instagramProfileId)
    resultObject.put("extra_info",extraInfo)
    resultObject.put("facebook_profile_link",facebookProfileLink)
    resultObject.put("instagram_profile_link",instagramProfileLink)
    resultObject.put("name",name)
    resultObject.putNullIfEmpty("phone",phone)
    resultObject.put("photo_link",photoLink)
    resultObject.put("push_token",pushToken)
    resultObject.put("vk_profile_link",vkProfilelink)

    return resultObject
}