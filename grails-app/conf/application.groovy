
server.contextPath = '/TrainingApplication'
server.port = 7075

//to allow hibernate generate sequance for every table
grails.gorm.default.mapping = {
	id generator: 'org.hibernate.id.enhanced.SequenceStyleGenerator', params: [prefer_sequence_per_entity: true]
}


grails.databinding.dateFormats = ["dd/MM/yyyy"]

