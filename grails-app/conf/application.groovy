
server.contextPath = '/TrainingApplication'
server.port = 7075

//to allow hibernate generate sequance for every table
grails.gorm.default.mapping = {
	id generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator', params: [prefer_sequence_per_entity: true]
}


grails.databinding.dateFormats = ["dd/MM/yyyy"]



// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'edu.training.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'edu.training.security.UserRole'
grails.plugin.springsecurity.authority.className = 'edu.training.security.Role'
grails.plugin.springsecurity.requestMap.className = 'edu.training.security.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'

//persistentToken
grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'edu.training.security.PersistentLogin'

//manual
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/user/list'
grails.plugin.springsecurity.logout.postOnly = false

//password

//bcrypt
//grails.plugin.springsecurity.password.algorithm = 'bcrypt'
//grails.plugin.springsecurity.password.bcrypt.logrounds = 15

//SHA
grails.plugin.springsecurity.password.algorithm = 'SHA-256'
grails.plugin.springsecurity.password.hash.iterations = 1

//moved to data base

//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//	[pattern: '/',               access: ['permitAll']],
//	[pattern: '/error',          access: ['permitAll']],
//	[pattern: '/index',          access: ['permitAll']],
//	[pattern: '/index.gsp',      access: ['permitAll']],
//	[pattern: '/shutdown',       access: ['permitAll']],
//	[pattern: '/assets/**',      access: ['permitAll']],
//	[pattern: '/**/js/**',       access: ['permitAll']],
//	[pattern: '/**/css/**',      access: ['permitAll']],
//	[pattern: '/**/images/**',   access: ['permitAll']],
//	[pattern: '/**/favicon.ico', access: ['permitAll']]
//]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/reports/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


security {
	acl {
		active = true
		authority {
			changeOwnership       = 'ROLE_ADMIN'
			modifyAuditingDetails = 'ROLE_ADMIN'
			changeAclDetails      = 'ROLE_ADMIN'
		}
	}
}


// AuditLog Plugin config
grails {
	plugin {
		auditLog {
			grails.plugin.auditLog.auditDomainClassName = 'edu.training.audit.DataAudit'
			truncateLength = 50
		}
	}
}

grails.plugin.fields.disableLookupCache = true