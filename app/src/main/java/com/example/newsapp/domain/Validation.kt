package com.example.newsapp.domain

object Validation {

    fun validateLogEmail( logEmail: String): ValidationResult{
        return ValidationResult(
            (  (logEmail.isNotEmpty()) && (logEmail.length>5) )
        )
    }

    fun validateLogPassword(logPassword: String): ValidationResult{
        return ValidationResult(
            ((logPassword.isNotEmpty()) && (logPassword.length >=6))
        )
    }

    fun validateSignEmail(signEmail: String): ValidationResult{
        return ValidationResult(
            (signEmail.isNotEmpty() && signEmail.length >=5)
        )
    }


    fun validateSignPassword(signPassword: String): ValidationResult{
        return ValidationResult(
            ((signPassword.isNotEmpty()) && (signPassword.length >=6))
        )
    }

    fun validateSignConfirmPassword(signConfirmPassword: String): ValidationResult{
        return ValidationResult(
            ((signConfirmPassword.isNotEmpty()) && (signConfirmPassword.length >=6))
        )
    }
}

data class ValidationResult(val status: Boolean = false)