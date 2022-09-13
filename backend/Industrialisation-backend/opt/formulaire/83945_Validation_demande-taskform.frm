{"id":"1f6afc71-ed92-4f24-8e76-c04777988a92","name":"Validation_demande-taskform.frm",
"model":{"taskName":"Validation_demande","processId":"Immatriculation.immatriculation",
"properties":
[
    {"name":"Frais Dossier","typeInfo":
    {"type":"BASE","className":"java.lang.String","multiple":false},
    "metaData":{"entries":[{"name":"field-readOnly","value":true}]}}
    ,{"name":"Taxe co2","typeInfo":{"type":"BASE","className":"java.lang.String","multiple":false},
    "metaData":{"entries":[{"name":"field-readOnly","value":true}]}},
    {"name":"Taxe Parafiscale","typeInfo":{"type":"BASE","className":"java.lang.String","multiple":false},
    "metaData":{"entries":[{"name":"field-readOnly","value":true}]}},
    {"name":"Taxe regional","typeInfo":{"type":"BASE","className":"java.lang.String","multiple":false},
    "metaData":{"entries":[{"name":"field-readOnly","value":true}]}},
    {"name":"Total","typeInfo":{"type":"BASE","className":"java.lang.String","multiple":false},
    "metaData":{"entries":[{"name":"field-readOnly","value":true}]}}],
    "formModelType":"org.kie.workbench.common.forms.jbpm.model.authoring.task.TaskFormModel"},
    
    "fields":[{
        "id":"field_0476174478236259E12",
        "name":"Valider","label":"Valider",
        "required":false,"readOnly":true,
        "validateOnChange":true,
        "standaloneClassName":"java.lang.Boolean",
        "code":"CheckBox",
        "serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.checkBox.definition.CheckBoxFieldDefinition"},
        {
            "maxLength":100,
            "placeHolder":"Frais Dossier",
            "id":"field_0092212698082416E11",
            "name":"Frais Dossier",
            "label":"Frais Dossier",
            "required":false,
            "readOnly":true,
            "validateOnChange":true,
            "binding":"Frais Dossier",
            "standaloneClassName":"java.lang.String",
            "code":"TextBox",
            "serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"},
            
        {"maxLength":100,"placeHolder":"Taxe co2","id":"field_631664757779493E11","name":"Taxe co2","label":"Taxe co2","required":false,"readOnly":true,"validateOnChange":true,"binding":"Taxe co2","standaloneClassName":"java.lang.String","code":"TextBox","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"},
        {"maxLength":100,"placeHolder":"Taxe Parafiscale","id":"field_48163588159104E10","name":"Taxe Parafiscale","label":"Taxe Parafiscale","required":false,"readOnly":true,"validateOnChange":true,"binding":"Taxe Parafiscale","standaloneClassName":"java.lang.String","code":"TextBox","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"},
        {"maxLength":100,"placeHolder":"Taxe regional","id":"field_303955732770384E11","name":"Taxe regional","label":"Taxe regional","required":false,"readOnly":true,"validateOnChange":true,"binding":"Taxe regional","standaloneClassName":"java.lang.String","code":"TextBox","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"},
        {"maxLength":100,"placeHolder":"Total","id":"field_7967565186338763E11","name":"Total","label":"Total","required":false,"readOnly":true,"validateOnChange":true,"binding":"Total","standaloneClassName":"java.lang.String","code":"TextBox","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"}],"layoutTemplate":{"version":3,"style":"FLUID","layoutProperties":{},"rows":[{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.uberfire.ext.plugin.client.perspective.editor.layout.editor.HTMLLayoutDragComponent","properties":{"HTML_CODE":"\u003ch3\u003eInputs:\u003c/h3\u003e"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_0476174478236259E12","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_0092212698082416E11","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_631664757779493E11","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_48163588159104E10","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_303955732770384E11","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]},{"properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_7967565186338763E11","form_id":"1f6afc71-ed92-4f24-8e76-c04777988a92"},"parts":[]}]}]}]}}