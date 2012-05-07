package assignment



import org.junit.*
import grails.test.mixin.*

@TestFor(OpportunitiesController)
@Mock(Opportunities)
class OpportunitiesControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/opportunities/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.opportunitiesInstanceList.size() == 0
        assert model.opportunitiesInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.opportunitiesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.opportunitiesInstance != null
        assert view == '/opportunities/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/opportunities/show/1'
        assert controller.flash.message != null
        assert Opportunities.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/opportunities/list'


        populateValidParams(params)
        def opportunities = new Opportunities(params)

        assert opportunities.save() != null

        params.id = opportunities.id

        def model = controller.show()

        assert model.opportunitiesInstance == opportunities
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/opportunities/list'


        populateValidParams(params)
        def opportunities = new Opportunities(params)

        assert opportunities.save() != null

        params.id = opportunities.id

        def model = controller.edit()

        assert model.opportunitiesInstance == opportunities
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/opportunities/list'

        response.reset()


        populateValidParams(params)
        def opportunities = new Opportunities(params)

        assert opportunities.save() != null

        // test invalid parameters in update
        params.id = opportunities.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/opportunities/edit"
        assert model.opportunitiesInstance != null

        opportunities.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/opportunities/show/$opportunities.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        opportunities.clearErrors()

        populateValidParams(params)
        params.id = opportunities.id
        params.version = -1
        controller.update()

        assert view == "/opportunities/edit"
        assert model.opportunitiesInstance != null
        assert model.opportunitiesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/opportunities/list'

        response.reset()

        populateValidParams(params)
        def opportunities = new Opportunities(params)

        assert opportunities.save() != null
        assert Opportunities.count() == 1

        params.id = opportunities.id

        controller.delete()

        assert Opportunities.count() == 0
        assert Opportunities.get(opportunities.id) == null
        assert response.redirectedUrl == '/opportunities/list'
    }
}
