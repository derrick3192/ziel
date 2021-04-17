package com.oe.ziel.dsl

import com.oe.ziel.dsl.export.GanttPrinter
import com.oe.ziel.dsl.export.YumlGanttPrinter
import com.oe.ziel.dsl.model.Gantt
import com.oe.ziel.dsl.model.dsl.GanttContentDefinition
import com.oe.ziel.dsl.model.dsl.GanttDefinition
import groovy.transform.CompileStatic
import spock.lang.Specification
import spock.lang.Unroll

import static com.oe.ziel.dsl.model.dsl.DiagramKeywords.*

class GanttSpec extends Specification {

    private static final String EXPECTED_DIAGRAM = '''
        [note: You can stick notes on diagrams too!{bg:skyblue}]
        [Customer]<>1-orders 0..*>[Order]
        [Order]++*-*>[LineItem]
        [Order]-1>[DeliveryMethod]
        [Order]*-*>[Product]
        [Category]<->[Product]
        [DeliveryMethod]^[National]
        [DeliveryMethod]^[International]
    '''.stripIndent().trim()

    private static final String EXPECTED_DIAGRAM_DIAGRAM = '''
        [note: YUML Diagram Components]
        [Type]<>1..*->[Diagram]
        [Note]<>0..*->[Diagram]
        [Relationship]<>0..*->[Diagram]
        [Type]<>source 1->[Relationship]
        [Type]<>destination 1->[Relationship]
        [RelationshipType]++1->[Relationship]
    '''.stripIndent().trim()

    private static final String EXPECTED_DIAGRAM_STEROTYPES = '''
        [note: YUML Diagram Components]
        [<<Type>>]<>1..*->[<<Diagram>>]
        [<<Note>>]<>0..*->[<<Diagram>>]
        [<<Relationship>>]<>0..*->[<<Diagram>>]
        [<<Type>>]<>source 1->[<<Relationship>>]
        [<<Type>>]<>destination 1->[<<Relationship>>]
        [<<RelationshipType>>]++1->[<<Relationship>>]
    '''.stripIndent().trim()

    private static final String EXPECTED_DIAGRAM_PROPERTIES = '''
        [note: YUML Diagram Components]
        [Type|name:string]<>1..*->[Diagram]
        [Note]<>0..*->[Diagram]
        [Relationship]<>0..*->[Diagram]
        [Type|name:string]<>source 1->[Relationship]
        [Type|name:string]<>destination 1->[Relationship]
        [RelationshipType]++1->[Relationship]
    '''.stripIndent().trim()

    @Unroll
    void 'create #title diagram'() {
        given:
            GanttPrinter printer = new YumlGanttPrinter()
        expect:
            printer.print(diagram).trim() == expected

            diagram.relationships*.source.every { it in diagram.types }
            diagram.relationships*.destination.every { it in diagram.types }
        where:
            title                           | diagram                                 | expected
            'orders'                        | buildOrderDiagram()                     | EXPECTED_DIAGRAM
            'literal diagram'               | buildDiagramDiagramLiteral()            | EXPECTED_DIAGRAM_DIAGRAM
            'literal diagram'               | buildDiagramDiagramGrouped()            | EXPECTED_DIAGRAM_DIAGRAM
            'diagram with methods'          | buildDiagramDiagramUsingHelperMethods() | EXPECTED_DIAGRAM_DIAGRAM
            'diagram with internal methods' | buildDiagramWithInternalMethodCalls()   | EXPECTED_DIAGRAM_DIAGRAM
            'diagram with stereotypes'      | buildDiagramStereotypes()               | EXPECTED_DIAGRAM_STEROTYPES
            'diagram with properties'       | buildDiagramProperties()                | EXPECTED_DIAGRAM_PROPERTIES
    }

    @CompileStatic
    private static Gantt buildOrderDiagram() {
        Gantt.build {
            note('You can stick notes on diagrams too!', 'skyblue')

            aggregation('Customer', 'Order') {
                source cardinality: '1'
                destination cardinality: '0..*', title: 'orders'
            }

            composition('Order', 'LineItem') {
                source '*'
                destination '*'
            }

            association('Order', 'DeliveryMethod') {
                destination '1'
            }

            association('Order', 'Product') {
                source '*'
                destination '*'
            }

            association('Category', 'Product') {
                bidirectional true
            }

            type 'National' inherits from type 'DeliveryMethod'
            type'International' inherits from type 'DeliveryMethod'
        }
    }

    @CompileStatic
    private static Gantt buildDiagramDiagramLiteral() {
        Gantt.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram' has one to many type 'Type'
            type 'Diagram' has zero to many type 'Note'
            type 'Diagram' has zero to many type 'Relationship'

            type 'Relationship' has one type 'Type' called 'source'
            type 'Relationship' has one type 'Type' called 'destination'
            type 'Relationship' owns one type 'RelationshipType'
        }
    }

    @CompileStatic
    private static Gantt buildDiagramDiagramGrouped() {
        Gantt.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram', {
                has one to many type 'Type'
                has zero to many type 'Note'
                has zero to many type 'Relationship'
            }

            type 'Relationship', {
                has one type 'Type' called 'source'
                has one type 'Type' called 'destination'
                owns one type 'RelationshipType'
            }
        }
    }

    @CompileStatic
    private static Gantt buildDiagramDiagramUsingHelperMethods() {
        Gantt.build { GanttDefinition diagram ->
            note 'YUML Diagram Components'

            buildDiagramRelationships(diagram)
            buildRelationshipRelationship(diagram)
        }
    }

    @CompileStatic
    private static GanttContentDefinition buildDiagramRelationships(GanttDefinition gantt) {
        gantt.with {
            type 'Diagram' has one to many type 'Type'
            type 'Diagram' has zero to many type 'Note'
            type 'Diagram' has zero to many type 'Relationship'
        }
    }

    @CompileStatic
    private static GanttContentDefinition buildRelationshipRelationship(GanttDefinition gantt) {
        gantt.with {
            type 'Relationship' has one type 'Type' called 'source'
            type 'Relationship' has one type 'Type' called 'destination'
            type 'Relationship' owns one type 'RelationshipType'
        }
    }

    @CompileStatic
    private static Gantt buildDiagramWithInternalMethodCalls() {
        Gantt.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram', {
                has one to many type 'Type'
                has zero to many type notes
                has zero to many type 'Relationship'
            }

            type 'Relationship', {
                has one type 'Type' called 'source'
                has one type 'Type' called 'destination'
                owns one type 'RelationshipType'
            }
        }
    }

    @CompileStatic
    private static Gantt buildDiagramStereotypes() {
        Gantt.build {
            note 'YUML Diagram Components'

            // diagram should have at least one stereotype to be meaningful, rest is optional
            stereotype 'Diagram' has one to many stereotype 'Type'
            stereotype 'Diagram' has zero to many stereotype 'Note'
            stereotype 'Diagram' has zero to many stereotype 'Relationship'

            stereotype 'Relationship' has one stereotype 'Type' called 'source'
            stereotype 'Relationship' has one stereotype 'Type' called 'destination'
            stereotype 'Relationship' owns one stereotype 'RelationshipType'
        }
    }

    @CompileStatic
    private static Gantt buildDiagramProperties() {
        Gantt.build {
            note 'YUML Diagram Components'

            // diagram should have at least one type to be meaningful, rest is optional
            type 'Diagram', {
                has one to many type 'Type'
                has zero to many type notes
                has zero to many type 'Relationship'
            }

            type 'Relationship', {
                has one type 'Type' called 'source'
                has one type 'Type' called 'destination'
                owns one type 'RelationshipType'
            }

            type 'Type', {
                property name: 'string'
            }
        }
    }

    private static String getNotes() {
        return 'Note'
    }
}
