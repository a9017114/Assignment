package assignment

import org.springframework.dao.DataIntegrityViolationException

class OpportunitiesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [opportunitiesInstanceList: Opportunities.list(params), opportunitiesInstanceTotal: Opportunities.count()]
    }

    def create() {
        [opportunitiesInstance: new Opportunities(params)]
    }

    def save() {
        def opportunitiesInstance = new Opportunities(params)
        if (!opportunitiesInstance.save(flush: true)) {
            render(view: "create", model: [opportunitiesInstance: opportunitiesInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), opportunitiesInstance.id])
        redirect(action: "show", id: opportunitiesInstance.id)
    }

    def show() {
        def opportunitiesInstance = Opportunities.get(params.id)
        if (!opportunitiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "list")
            return
        }

        [opportunitiesInstance: opportunitiesInstance]
    }

    def edit() {
        def opportunitiesInstance = Opportunities.get(params.id)
        if (!opportunitiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "list")
            return
        }

        [opportunitiesInstance: opportunitiesInstance]
    }

    def update() {
        def opportunitiesInstance = Opportunities.get(params.id)
        if (!opportunitiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (opportunitiesInstance.version > version) {
                opportunitiesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'opportunities.label', default: 'Opportunities')] as Object[],
                          "Another user has updated this Opportunities while you were editing")
                render(view: "edit", model: [opportunitiesInstance: opportunitiesInstance])
                return
            }
        }

        opportunitiesInstance.properties = params

        if (!opportunitiesInstance.save(flush: true)) {
            render(view: "edit", model: [opportunitiesInstance: opportunitiesInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), opportunitiesInstance.id])
        redirect(action: "show", id: opportunitiesInstance.id)
    }

    def delete() {
        def opportunitiesInstance = Opportunities.get(params.id)
        if (!opportunitiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "list")
            return
        }

        try {
            opportunitiesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'opportunities.label', default: 'Opportunities'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
