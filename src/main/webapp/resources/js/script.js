// Esperar a que cargue la página
$(function() {
    // Asignar el evento 'click' a todos los elementos con 'data-id'
    $('[data-id]').on('click', function() {
        // Extraer id
        let id = $(this).data('id');

        // Redireccionar
        $(location).attr('href', '/periodico/posts/' + id);
    });
});
