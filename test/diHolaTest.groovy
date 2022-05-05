// test/diHolaTest.groovy
// Importa lo necesario: JUnit y Jenkins Pipeline Unit
import org.junit.*
import static org.junit.Assert.*
import com.lesfurets.jenkins.unit.*
 
// Hereda de BasePipelineTest para usar el framework Jenkins Pipeline Unit
class logErrorTest extends BasePipelineTest {
    // creamos una variable como lo que se va a probar
    def diHola
 
    // Antes de que la prueba se lance se hace esto:
    @Before
    void setUp() {
        super.setUp()
        // Cargamos el script, sin ejecutarlo
        diHola = loadScript("vars/diHola.groovy")
    }
 
    // Esta es la prueba
    @Test
    void 'Log message to console with "Hola" prepended'() {
        // Se ejecuta el método 'call' de la clase bajo test
        diHola.call("message")
        // Validar que sólo se hace echo una vez
        assertEquals(1, helper.methodCallCount('echo'))
        // Validar que la call contiene el texto "Hola"
        assertTrue(helper.getCallStack()[1].args[0].toString().contains("Hola"))
        // Imprimir la pila de llamadas completa en la consola para mejores comprobaciones
        printCallStack()
    }
 
}