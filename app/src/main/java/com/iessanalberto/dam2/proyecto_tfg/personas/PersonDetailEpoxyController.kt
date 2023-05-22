//package com.iessanalberto.dam2.proyecto_tfg.personas
//
//
//import com.airbnb.epoxy.EpoxyController
//import com.iessanalberto.dam2.proyecto_tfg.R
//import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelPersonDetailImageBinding
//import com.iessanalberto.dam2.proyecto_tfg.databinding.ModelPersonDetailsHeaderBinding
//import com.iessanalberto.dam2.proyecto_tfg.dominio.modelos.Person
//import com.iessanalberto.dam2.proyecto_tfg.epoxy.CargaModelos
//import com.iessanalberto.dam2.proyecto_tfg.epoxy.ViewBindingKotlinModel
//import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
//import com.squareup.picasso.Picasso
//
//class PersonDetailEpoxyController : EpoxyController() {
//
//    var isLoading: Boolean = true
//        set(value) {
//            field = value
//            if (field) {
//                requestModelBuild()
//            }
//        }
//
//    var person: Person? = null
//        set(value) {
//            field = value
//            if (field != null) {
//                isLoading = false
//                requestModelBuild()
//            }
//        }
//
//    override fun buildModels() {
//        if (isLoading) {
//            CargaModelos().id("load").addTo(this)
//            return
//        }
//
//        if (person == null) {
//            return
//        }
//
//
//        ImageEpoxyModel(
//            actorImg = Constantes.POSTER_PATH + person!!.profile_path
//        ).id("actorImage").addTo(this)
//
//        HeaderTextEpoxyModel(
//            nombre = person!!.name,
//            fecha = person!!.birthday,
//            lugar = person!!.place_of_birth,
//            conocidoPor = person!!.known_for_department,
//            biografia = person!!.biography,
//            actorSex = person!!.gender
//
//        ).id("header").addTo(this)
//
//    }
//
//    data class ImageEpoxyModel(
//        val actorImg: String
//        ) : ViewBindingKotlinModel<ModelPersonDetailImageBinding>(R.layout.model_person_detail_image) {
//        override fun ModelPersonDetailImageBinding.bind() {
//            Picasso.get().load(actorImg).into(personImageView)
//        }
//    }
//
//    data class HeaderTextEpoxyModel(
//        val nombre: String,
//        val fecha: String,
//        val lugar: String,
//        val conocidoPor: String,
//        val biografia: String,
//        val actorSex: Int
//    ) : ViewBindingKotlinModel<ModelPersonDetailsHeaderBinding>(R.layout.model_person_details_header) {
//        override fun ModelPersonDetailsHeaderBinding.bind() {
//
//            actorNameTextView.text = nombre
//            birthTextView.text = fecha
//            cityTextView.text = lugar
//            knownTextView.text = conocidoPor
//            biographyTextView.text = biografia
//
//            if (actorSex == 2){
//                sexImageView.setImageResource(R.drawable.baseline_male_24)
//            }else {
//                sexImageView.setImageResource(R.drawable.baseline_female_24)
//            }
//
//            if (biographyTextView.text.isEmpty()) {
//                biographyTextView.text = "Esta persona no tiene biografía en español, puede añadir una biografía a esta persona en la página oficial de TheMovieDB."
//            }
//        }
//    }
//
//}